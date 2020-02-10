package com.reagroup.toyrobot.model.robot;

import com.reagroup.toyrobot.model.command.MoveCommand;
import com.reagroup.toyrobot.model.command.PlaceCommand;
import com.reagroup.toyrobot.model.state.Movement;
import com.reagroup.toyrobot.model.state.Orientation;
import com.reagroup.toyrobot.model.state.Position;
import com.reagroup.toyrobot.model.state.State;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class RobotTest {

    private IRobot robot;

    @Before
    public void initial() {
        this.robot = new RobotBuilder().setMandatoryBoundary(new Position(3, 3)).build();
    }

    @Test
    public void testTakeOff() throws Exception {
        Movement movement = this.robot.apply(new MoveCommand());
        assertNull(movement.updatedState());
    }


    @Test
    public void testMove() throws Exception {
        this.robot.apply(new PlaceCommand(1, 1, Orientation.NORTH));
        Movement movement = this.robot.apply(new MoveCommand());
        assertEquals(new State(1, 2, Orientation.NORTH), movement.updatedState());
    }

    @Test
    public void testInvalidMove() throws Exception {
        this.robot.apply(new PlaceCommand(0, 0, Orientation.SOUTH));
        Movement movement = this.robot.apply(new MoveCommand());
        assertEquals(new State(0, 0, Orientation.SOUTH), movement.updatedState());
    }

    @Test
    public void testBoundary() throws Exception {
        this.robot.apply(new PlaceCommand(0, 0, Orientation.NORTH));
        Movement movement = this.robot.apply(new MoveCommand());
        assertEquals(new State(0, 1, Orientation.NORTH), movement.updatedState());
    }

    @Test
    public void testBoundaryAndToString() throws Exception {
        assertEquals(new Position(3, 3), this.robot.boundary());
        assertEquals("Current robot's state: Not placed", robot.toString());
        this.robot.apply(new PlaceCommand(0, 0, Orientation.NORTH));
        Movement movement = this.robot.apply(new MoveCommand());
        assertEquals(new State(new Position(0, 0), Orientation.NORTH), movement.originalState());
        assertEquals("Current robot's state: 0,1,NORTH", robot.toString());
    }

    @Test
    public void testEqualForPlaceCommand() throws Exception {
        Movement movement = this.robot.apply(new PlaceCommand(0, 0, Orientation.NORTH));
        assertEquals(this.robot.state(), movement.updatedState());
    }

    @Test
    public void testEqualForMoveCommand() throws Exception {
        this.robot.apply(new PlaceCommand(0, 0, Orientation.NORTH));
        Movement movement = this.robot.apply(new MoveCommand());
        assertEquals(this.robot.state(), movement.updatedState());
    }


















}
