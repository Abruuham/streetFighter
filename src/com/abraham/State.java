package com.abraham;

import java.awt.*;

public abstract class State {

    private static State currentState = null;
    private Main game;

    /**
     * Default constructor
     * @param game
     */
    public State(Main game){
        this.game = game;
    }

    public static void setState(State state) {
        currentState = state;
    }

    public static State getState(){
        return currentState;
    }

    public abstract void render(Graphics g);

    public abstract void timer();

    public abstract Rectangle getPlayerOneHitBounds();
    public abstract Rectangle getPlayerTwoHitBounds();




}
