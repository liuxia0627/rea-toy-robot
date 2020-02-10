package com.reagroup.toyrobot.model.robot;

import com.reagroup.toyrobot.model.state.Position;
import com.reagroup.toyrobot.model.state.State;

/**
 * Adopt Builder pattern to create the toy robot
 * boundary is mandatory initial parameter which can be set in the constructor
 */
public class RobotBuilder {

    private Position boundary;
    private State state;

    /**
     *
     * @param position position of boundary
     * @return RobotBuilder
     */
    public RobotBuilder setMandatoryBoundary(Position position){
        this.boundary = position;
        return this;
    }

    public Robot build() {
        if (this.boundary == null) {
            throw new IllegalStateException("Boundary should be set for the toy to move");
        }
        return new Robot(this.state, this.boundary);
    }

}
