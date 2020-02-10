package com.reagroup.toyrobot.model.command;

import com.reagroup.toyrobot.model.state.State;

/**
 * Takeoff command takes the toy robot off the tabletop
 */
public class TakeoffCommand implements Command {
    @Override
    public State apply(State state) {
        return null;
    }
}
