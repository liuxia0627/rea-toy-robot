package com.reagroup.toyrobot.model.args;

import com.reagroup.toyrobot.model.command.*;
import com.reagroup.toyrobot.model.state.Orientation;
import com.reagroup.toyrobot.model.state.State;
import org.junit.Before;
import org.junit.Test;

import static com.reagroup.toyrobot.constants.Constants.*;
import static org.junit.Assert.*;

public class CommandParserTest {

    private CommandParser commandParser;
    private State fakeState;

    @Before
    public void initial() {
        this.commandParser = new CommandParser();
        this.fakeState = new State(0, 0, Orientation.NORTH);
    }

    @Test
    public void testLeftCommand() throws Exception{
        Command command = commandParser.apply(LEFT_COMMAND);
        assertTrue(command instanceof LeftCommand);
    }

    @Test
    public void testRightCommand() throws Exception {
        Command command = commandParser.apply(RIGHT_COMMAND);
        assertTrue(command instanceof RightCommand);
    }

    @Test
    public void testMoveCommand() throws Exception {
        Command command = commandParser.apply(MOVE_COMMAND);
        assertTrue(command instanceof MoveCommand);
    }

    @Test
    public void testPlaceCommand() throws Exception {
        Command command = commandParser.apply("PLACE 0,0,NORTH");
        assertTrue(command instanceof PlaceCommand);
    }

    @Test
    public void testReportCommand() throws Exception {
        Command command = commandParser.apply(REPORT_COMMAND);
        assertTrue(command instanceof ReportCommand);
    }

    @Test
    public void TestValidPlaceCommand1() throws Exception {
        Command command = commandParser.apply("PLACE 0,0,NORTH");
        State state = command.apply(this.fakeState);
        assertEquals(new State(0, 0, Orientation.NORTH), state);
    }

    @Test
    public void testValidPlaceCommand2() throws Exception {
        Command command = commandParser.apply("PLACE 2,3,NORTH");
        State state = command.apply(this.fakeState);
        assertEquals(new State(2, 3, Orientation.NORTH), state);
    }

    @Test
    public void testValidPlaceCommand3() throws Exception {
        Command command = commandParser.apply("PLACE 3,2,WEST");
        State state = command.apply(this.fakeState);
        assertNotEquals(new State(2,3, Orientation.EAST), state);
    }

    @Test
    public void testInvalidPlaceCommand1() throws Exception {
        Command command = commandParser.apply("PLACE5,3,EAST");
        State state = command.apply(this.fakeState);
        assertEquals(state, this.fakeState);
    }

    @Test
    public void testInvalidPlaceCommand2() throws Exception {
        Command command = commandParser.apply("PLACE 3, 2, NORTH");
        State state = command.apply(this.fakeState);
        assertEquals(state, this.fakeState);
    }

    @Test
    public void testInvalidPlaceCommand3() throws Exception {
        Command command = commandParser.apply("PLACE1,1, COMMAND");
        State state = command.apply(this.fakeState);
        assertEquals(state, this.fakeState);
    }

    @Test
    public void testInvalidPlaceCommand4() throws Exception {
        Command command = commandParser.apply("PLACE 0,0,NORT");
        State state = command.apply(this.fakeState);
        assertEquals(state, this.fakeState);
    }

    @Test
    public void testTakeOffCommand() throws Exception {
        Command command = commandParser.apply(TAKEOFF_COMMAND);
        State state = command.apply(this.fakeState);
        assertNull(state);
    }

    @Test
    public void testEmptyCommand() throws Exception {
        Command command = commandParser.apply("");
        State state = command.apply(this.fakeState);
        assertEquals(state, this.fakeState);
    }

    @Test
    public void testInvalidCommand() throws Exception {
        Command command = commandParser.apply("INVALID COMMAND");
        State state = command.apply(this.fakeState);
        assertEquals(state, this.fakeState);
    }

}
