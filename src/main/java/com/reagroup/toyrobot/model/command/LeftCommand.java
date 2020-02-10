package com.reagroup.toyrobot.model.command;

import com.reagroup.toyrobot.model.state.State;

public class LeftCommand extends AbstractCommand{

    @Override
    public State applyInternal(State state) {
        return new State(state.position(), state.orientation().leftRotate());
    }
}
