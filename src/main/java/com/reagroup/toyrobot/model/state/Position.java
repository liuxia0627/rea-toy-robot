package com.reagroup.toyrobot.model.state;

import java.util.Objects;

/**
 * Immutable model object represents the position of a toy robot.
 * The position is composed of x and y coordinates.
 */

public class Position {

    private final int x;
    private final int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int x() {
        return this.x;
    }

    public int y() {
        return this.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return this.x() + "," + this.y();
    }
}
