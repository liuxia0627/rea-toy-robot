package com.reagroup.toyrobot.model.command;

import com.reagroup.toyrobot.model.state.State;

/**
 * Triggers no movement to the robot and notifies the simulator's main method to make a report
 */

public class ReportCommand extends AbstractCommand {
    @Override
    public State applyInternal(State state) {
        return state;
    }
}
