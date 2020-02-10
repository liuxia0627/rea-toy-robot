package com.reagroup.toyrobot.model.robot;

import com.reagroup.toyrobot.model.command.Command;
import com.reagroup.toyrobot.model.state.Movement;
import com.reagroup.toyrobot.model.state.State;
import com.reagroup.toyrobot.model.state.Position;
import java.util.logging.Logger;

/**
 * Implementation class of Robot which implements IRobot interface
 */
public class Robot implements IRobot{

    private final static Logger LOGGER = Logger.getLogger(Robot.class.getName());
    private final Position boundary;
    private State state;

    /**
     *
     * @param state state of the robot
     * @param boundary boundary of the robot
     */
    Robot(State state, Position boundary){
        this.boundary = boundary;
        this.state = state;
    }

    /**
     * Execute the input command to the robot and return the movement
     * @param command Input command the robot should execute
     * @return Movement of the robot after command executed
     */
    @Override
    public Movement apply(Command command) {
        State originalState = this.state;
        State updatedState = command.apply(this.state);

        if(this.isValidState(updatedState)){
            this.state = updatedState;
            return new Movement(originalState, command, updatedState);
        }else{
            LOGGER.warning("New state is not valid, can not be updated");
            return new Movement(originalState, command, originalState);
        }
    }

    /**
     *
     * @param state current state of the robot to be validated
     * @return Return true when the robot's state is within boundary, return false when the robot's sate is beyond boundary
     */

    private boolean isValidState(State state){
        if(state == null){
            return true;
        }

        Position position = state.position();
        return position.x() >=0 &&
                position.y() >=0 &&
                position.x() < boundary.x() &&
                position.y() < boundary.y();
    }

    public State state() {return state;}

    public Position boundary() {return boundary;}

    @Override
    public String toString() {
        return "Current robot's state: " + (state !=null ? state.toString() : "Not placed");
    }
}
