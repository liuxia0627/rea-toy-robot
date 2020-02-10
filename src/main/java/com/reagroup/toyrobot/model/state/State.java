package com.reagroup.toyrobot.model.state;

import java.util.Objects;

/**
 * Immutable model object represents a toy's position and orientation
 */

public class State {

    private final Orientation orientation;
    private final Position position;

    public State(Position position, Orientation orientation) {
        this.position = position;
        this.orientation = orientation;
    }

    public State(int x, int y, Orientation orientation) {
        this.position = new Position(x, y);
        this.orientation = orientation;
    }

    public Orientation orientation() {
        return this.orientation;
    }

    public Position position() {
        return this.position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return orientation == state.orientation &&
                Objects.equals(position, state.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orientation, position);
    }

    @Override
    public String toString() {
        return this.position() + "," + this.orientation();
    }
}
