package com.reagroup.toyrobot.model.command;

import com.reagroup.toyrobot.model.state.Orientation;
import com.reagroup.toyrobot.model.state.Position;
import com.reagroup.toyrobot.model.state.State;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CommandTest {

    private State state;
    private State placeState;
    private Command leftCommand;
    private Command rightCommand;
    private Command moveCommand;
    private Command placeCommand;
    private Command reportCommand;
    private Command takeoffCommand;

    @Before
    public void initial() {
        this.state = new State(new Position(3, 3), Orientation.SOUTH);
        this.placeState = new State(new Position(0, 0), Orientation.NORTH);
        this.leftCommand = new LeftCommand();
        this.rightCommand = new RightCommand();
        this.moveCommand = new MoveCommand();
        this.placeCommand = new PlaceCommand(this.placeState);
        this.reportCommand = new ReportCommand();
        this.takeoffCommand = new TakeoffCommand();
    }

    @Test
    public void testLeftCommand(){
        assertEquals(new State(3, 3, Orientation.EAST), this.leftCommand.apply(this.state));
        assertEquals(new State(3, 3, Orientation.NORTH), this.leftCommand.apply(new State(new Position(3, 3), Orientation.EAST)));
        assertEquals(new State(3, 3, Orientation.SOUTH), this.leftCommand.apply(new State(new Position(3, 3), Orientation.WEST)));
    }

    @Test
    public void testMoveCommand() {
        assertEquals(new State(3, 2, Orientation.SOUTH), this.moveCommand.apply(this.state));
    }

    @Test
    public void testPlaceCommand() {
        assertEquals(this.placeState, this.placeCommand.apply(this.state));
    }

    @Test
    public void testReportCommand() {
        assertEquals(new State(3, 3, Orientation.SOUTH), this.reportCommand.apply(this.state));
    }

    @Test
    public void testRightCommand() {
        assertEquals(new State(3, 3, Orientation.WEST), this.rightCommand.apply(this.state));
        assertEquals(new State(3, 3, Orientation.EAST), this.rightCommand.apply(new State(new Position(3, 3), Orientation.NORTH)));
        assertEquals(new State(3, 3, Orientation.SOUTH), this.rightCommand.apply(new State(new Position(3, 3), Orientation.EAST)));
    }

    @Test
    public void testTakeoffCommand() {
        assertNull(this.takeoffCommand.apply(this.state));
    }

}
