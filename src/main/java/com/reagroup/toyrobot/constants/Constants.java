package com.reagroup.toyrobot.constants;

import java.util.regex.Pattern;

public class Constants {
    public final static String LEFT_COMMAND = "LEFT";
    public final static String RIGHT_COMMAND = "RIGHT";
    public final static String MOVE_COMMAND = "MOVE";
    public final static String PLACE_COMMAND = "PLACE";
    public final static String TAKEOFF_COMMAND = "TAKEOFF";
    public final static String REPORT_COMMAND = "REPORT";
    public static final Pattern PLACE_REGEX = Pattern.compile(PLACE_COMMAND + " (\\d+),(\\d+),(\\w+)");
    public static final String ORIENTATION_NORTH = "NORTH";
    public static final String ORIENTATION_SOUTH = "SOUTH";
    public static final String ORIENTATION_EAST = "EAST";
    public static final String ORIENTATION_WEST = "WEST";
}
