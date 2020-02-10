package com.reagroup.toyrobot.model.command;

import com.reagroup.toyrobot.model.state.State;

import java.util.logging.Logger;

public class MoveCommand extends AbstractCommand {
    private final static Logger LOGGER = Logger.getLogger(MoveCommand.class.getName());

    @Override
    protected State applyInternal(State state) {
        switch (state.orientation()){
            case NORTH:
                return new State(state.position().x(), state.position().y()+1, state.orientation());
            case EAST:
                return new State(state.position().x()+1, state.position().y(), state.orientation());
            case SOUTH:
                return new State(state.position().x(), state.position().y()-1, state.orientation());
            case WEST:
                return new State(state.position().x()-1, state.position().y(), state.orientation());
            default:
                LOGGER.warning("Invalid Orientation: " + state.orientation() +", Toy Robot will not move.");
                return state;
        }
    }
}
