package com.reagroup.toyrobot.model.command;

import com.reagroup.toyrobot.model.state.State;

/**
 * Abstract class provides the common function
 * that no movements will be caused if applying command on a non-placed robot,
 * so that the implementors of command interface don't have to deal with this scenario
 */

abstract class AbstractCommand implements Command{

    @Override
    public final State apply(State state) {
        if(state == null){
            return null;
        }
        return this.transit(state);
    }

    /**
     * Transform
     *
     * @param state the input state
     * @return the updated state
     */
    protected abstract State transit(State state);
}
