package com.reagroup.toyrobot.model.args;

import com.reagroup.toyrobot.model.command.*;
import com.reagroup.toyrobot.model.state.Orientation;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.regex.Matcher;

import static com.reagroup.toyrobot.constants.Constants.*;

/**
 * Parses a command String into a command object
 */


public class CommandParser implements Function<String, Command>  {
    private final static Logger LOGGER = Logger.getLogger(CommandParser.class.getName());


    /**
     * Parses a command String to a command Object
     * @param commandString input command string
     * @return parsed executable command object
     */
    @Override
    public Command apply(String commandString) {
        if (commandString == null || commandString.isEmpty()) {
            LOGGER.warning("Command cannot be empty");
            return new NullCommand();
        } else if (commandString.equals(LEFT_COMMAND)) {
            return new LeftCommand();
        } else if (commandString.equals(MOVE_COMMAND)) {
            return new MoveCommand();
        } else if (commandString.startsWith(PLACE_COMMAND)) {
            return this.parsePlaceCommand(commandString);
        } else if (commandString.equals(REPORT_COMMAND)) {
            return new ReportCommand();
        } else if (commandString.equals(RIGHT_COMMAND)) {
            return new RightCommand();
        } else if (commandString.equals(TAKEOFF_COMMAND)) {
            return new TakeoffCommand();
        }
        else {
            LOGGER.warning("Invalid command: " + commandString + "; doing nothing.");
            return new NullCommand();
        }
    }

    /**
     * Parses place command String to a executable place command object
     * @param commandString place command String
     * @return executable place command object
     */
    private Command parsePlaceCommand(String commandString) {
        Matcher matcher = PLACE_REGEX.matcher(commandString);
        if(matcher.matches()){
            return createPlaceCommand(matcher);
        } else {
            LOGGER.warning("Invalid command: " + commandString + "; doing nothing.");
            return new NullCommand();
        }
    }

    /**
     * Create place command based on matched place command String args
     * @param matcher matched place command string args
     * @return executable place command object
     */
    private Command createPlaceCommand (Matcher matcher) {
    try{
        return new PlaceCommand(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), this.parsePlaceCommandOrientation(matcher.group(3)));
    } catch (IllegalArgumentException ex){
        LOGGER.warning(ex.getMessage());
        return new NullCommand();
    }

    }

    /**
     * Parse and get the orientation based on matched place command string args
     * @param orientationString matched orientation command string args
     * @return  orientation of the place command object
     */
    private Orientation parsePlaceCommandOrientation(String orientationString) {
        if(orientationString == null || orientationString.isEmpty()){
            throw new IllegalArgumentException("Orientation cannot be empty");
        }
        switch (orientationString){
            case ORIENTATION_NORTH:
                return Orientation.NORTH;
            case ORIENTATION_EAST:
                return Orientation.EAST;
            case ORIENTATION_SOUTH:
                return Orientation.SOUTH;
            case ORIENTATION_WEST:
                return Orientation.WEST;
            default:
                throw new IllegalArgumentException("Invalid Orientation: " + orientationString);

        }
    }






















}
