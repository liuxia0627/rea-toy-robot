package com.reagroup.toyrobot.model.state;

/**
 * Enum represents the four possible orientations for a toy
 * Rotate left and right methods are also provided.
 */

public enum Orientation {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    /**
     * Left Rotate method to get the updated orientation based on current orientation after left rotate
     * @return updated orientation after left rotate
     */
    public Orientation leftRotate() {
        switch (this){
            case NORTH:
                return WEST;
            case EAST:
                return NORTH;
            case SOUTH:
                return EAST;
            case WEST:
                return SOUTH;
            default:
                throw new IllegalStateException("Invalid Orientation: " + this);
        }
    }

    /**
     * Right Rotate method to get the updated orientation based on current orientation after right rotate
     * @return updated orientation after right rotate
     */
    public Orientation rightRotate() {
        switch (this){
            case NORTH:
                return EAST;
            case EAST:
                return SOUTH;
            case SOUTH:
                return WEST;
            case WEST:
                return NORTH;
            default:
                throw new IllegalStateException("Invalid Orientation: " + this);
        }
    }
}
