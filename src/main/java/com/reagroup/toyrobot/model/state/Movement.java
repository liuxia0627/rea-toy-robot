package com.reagroup.toyrobot.model.state;

import com.reagroup.toyrobot.model.command.Command;

/**
 * Immutable model object represents a movement.
 * Contains original position, updated position and the command that transform from original one to the update one
 *
 */
public class Movement {

    private final State originalState;
    private final Command command;
    private final State updatedState;

    /**
     *
     * @param originalState The original position
     * @param command the command that made the movement from original to updated position
     * @param updatedState the update position
     */
    public Movement(State originalState, Command command, State updatedState){
        this.originalState = originalState;
        this.command = command;
        this.updatedState = updatedState;
    }

    public State originalState(){
        return this.originalState;
    }

    public State updatedState(){
        return this.updatedState;
    }

    public Command command(){
        return this.command;
    }
}
