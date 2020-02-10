package com.reagroup.toyrobot.model.robot;

import com.reagroup.toyrobot.model.command.Command;
import com.reagroup.toyrobot.model.state.Movement;
import com.reagroup.toyrobot.model.state.Position;
import com.reagroup.toyrobot.model.state.State;

import java.util.function.Function;

/**
 * Toy Robot model Interface
 */
public interface IRobot extends Function<Command, Movement> {

    /**
     * Return movement the robot should make based on input command
     * @param command Input command the robot should execute
     * @return Movement the robot should make based on the input command
     */
    @Override
    Movement apply(Command command);

    /**
     *
     * @return The current state of the robot, return null if there is no robot replaced
     */
    State state();

    /**
     *
     * @return The boundaries of the tabletop provided to the robot
     */
    Position boundary();
}
