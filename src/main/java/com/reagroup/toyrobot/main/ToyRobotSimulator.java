package com.reagroup.toyrobot.main;

import com.reagroup.toyrobot.model.command.ReportCommand;
import com.reagroup.toyrobot.model.args.CommandParser;
import com.reagroup.toyrobot.model.args.InputArgs;
import com.reagroup.toyrobot.model.robot.IRobot;
import com.reagroup.toyrobot.model.robot.Robot;
import com.reagroup.toyrobot.model.robot.RobotBuilder;
import com.reagroup.toyrobot.model.state.Movement;
import com.reagroup.toyrobot.model.state.Position;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main entry-point class to run Toy Robot Simulator
 */

public class ToyRobotSimulator {

    private static final Position BOUNDARY = new Position(4, 4);

    public static void main(String[] args) {
        configLogging();
        IRobot robot = buildRobot();
        executeSimulator(args, robot);
    }

    /**
     * Execute Robot Toy Simulator
     * @param args input command args
     * @param robot robot
     */
    private static void executeSimulator(String[] args, IRobot robot){
        final CommandParser commandParser = new CommandParser();
        new InputArgs().apply(args)
                .map(commandString -> commandParser.apply(commandString))
                .map(command -> robot.apply(command))
                .filter(movement -> movement.command() instanceof ReportCommand)
                .map(Movement::updatedState)
                .forEach(state -> System.out.println((state != null) ? state : "No Robot placed yet!"));
    }

    /**
     *
     * @return robot configured with specified boundary
     */
    private static Robot buildRobot() {
        return new RobotBuilder().setMandatoryBoundary(BOUNDARY).build();
    }

    /**
     * Configure message level for Logger
     */
    private static void configLogging() {
        Logger rootLog = Logger.getLogger("");
        rootLog.setLevel(Level.FINE);
        rootLog.getHandlers()[0].setLevel(Level.FINE);
    }
}
