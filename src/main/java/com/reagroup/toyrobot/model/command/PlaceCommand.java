package com.reagroup.toyrobot.model.command;

import com.reagroup.toyrobot.model.state.Orientation;
import com.reagroup.toyrobot.model.state.State;

/**
 * Place Command will output the state passed in the constructor
 */
public class PlaceCommand implements Command {
    private final State state;
    public PlaceCommand(int x, int y, Orientation orientation){
        this.state = new State(x, y, orientation);
    }

    public PlaceCommand(State state){
        this.state = state;
    }

    @Override
    public State apply(State state) {
        return this.state;
    }
}
