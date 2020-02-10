package com.reagroup.toyrobot.model.command;

import com.reagroup.toyrobot.model.state.State;
import java.util.function.UnaryOperator;

/**
 * Interface represents Command object which takes state as input and update it to an changed state
 */

public interface Command extends UnaryOperator<State> {

    /**
     * Update an input state
     *
     * @param state the input state
     * @return the updated input state
     */
    @Override
    State apply(State state);
}
