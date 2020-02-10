package com.reagroup.toyrobot.model.command;

import com.reagroup.toyrobot.model.state.State;

/**
 * null-object command returns unmodified state
 */

public class NullCommand extends  AbstractCommand{
    @Override
    public State applyInternal(State state) {
        return state;
    }
}
