package com.reagroup.toyrobot.model.main;

import com.reagroup.toyrobot.main.ToyRobotSimulator;
import com.reagroup.toyrobot.model.command.ReportCommand;
import com.reagroup.toyrobot.model.args.CommandParser;
import com.reagroup.toyrobot.model.robot.Robot;
import com.reagroup.toyrobot.model.robot.RobotBuilder;
import com.reagroup.toyrobot.model.state.Movement;
import com.reagroup.toyrobot.model.state.Position;
import com.reagroup.toyrobot.model.state.State;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class TestToyRobotSimulator {

    private Robot robot;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final InputStream originalIn = System.in;
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;
    private ByteArrayInputStream testIn;


    @Before
    public void initial() {
        this.robot = new RobotBuilder().setMandatoryBoundary(new Position(5, 5)).build();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testOneStepMove() throws Exception {
        assertEquals("1,0,EAST", triggerSimulator("PLACE 0,0,EAST","MOVE","REPORT"));
    }

    @Test
    public void testRotate() throws Exception {
        assertEquals("0,0,EAST", triggerSimulator("PLACE 0,0,SOUTH", "LEFT", "REPORT"));
    }

    @Test
    public void testMoveAndRotate() throws Exception {
        assertEquals("0,3,NORTH", triggerSimulator("PLACE 1,1,NORTH", "MOVE", "LEFT", "MOVE", "RIGHT", "MOVE", "REPORT"));
    }

    @Test
    public void testMainWithFilePath() throws Exception {
        List<String> commandsList = Arrays.asList("PLACE 1,1,NORTH", "MOVE", "LEFT", "MOVE", "RIGHT", "MOVE", "REPORT");
        Path file = Paths.get("commandA.txt");
        Files.write(file, commandsList, StandardCharsets.UTF_8);
        String[] args = new String[1];
        args[0] = "commandA.txt";
        ToyRobotSimulator.main(args);
        assertEquals("0,3,NORTH" +  System.lineSeparator(), outContent.toString());
        Files.delete(file);
    }

    @Test
    public void testMainWithInvalidFilePath() throws  Exception {
        List<String> commandsList = Arrays.asList("PLACE 1,1,NORTH", "MOVE", "LEFT", "MOVE", "RIGHT", "MOVE", "REPORT");
        Path file = Paths.get("commandA.txt");
        Files.write(file, commandsList, StandardCharsets.UTF_8);
        String[] args = new String[1];
        args[0] = "commandB.txt";
        ToyRobotSimulator.main(args);
        assertEquals("", outContent.toString());
    }

    @Test
    public void testMainWithInvalidArgs() throws Exception {
        String[] args = new String[2];
        args[0] = "test";
        args[1] = "invalid";
        ToyRobotSimulator.main(args);
        assertEquals("", outContent.toString());
     }

    @Test
    public void testMainWithCommandArgs() throws Exception {
        provideInput("PLACE 0,0,EAST"  +  System.lineSeparator() + "MOVE" + System.lineSeparator() + "REPORT");
        ToyRobotSimulator.main(new String[0]);
        assertEquals("Please enter the proper commands to start Toy Robot Simulator" + System.lineSeparator() + "Command line should be entered per line." + System.lineSeparator() + System.lineSeparator()  +"1,0,EAST" +  System.lineSeparator(), outContent.toString());
    }

    @Test
    public void testMainWithNoBoundaryConfigured() throws Exception {

        expectedEx.expect(IllegalStateException.class);
        expectedEx.expectMessage("Boundary should be set for the toy to move");
        Robot robotWithNoBoundaryConfigured = new RobotBuilder().setMandatoryBoundary(null).build();

    }

    private String triggerSimulator(String... commands) {
        List<String> output = Stream.of(commands)
                .map(new CommandParser())
                .map(this.robot)
                .filter(movement -> movement.command() instanceof ReportCommand)
                .map(Movement::updatedState)
                .map(State::toString)
                .collect(Collectors.toList());
        assertEquals(1, output.size());
        return output.get(0);
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
        System.setIn(originalIn);
    }
}

