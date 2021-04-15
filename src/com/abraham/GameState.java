package com.abraham;

import java.awt.*;

public class GameState extends State{

    private PlayerOne p1;
    private PlayerTwo p2;

    public GameState(Main game){
        super(game);
    }


    @Override
    public void render(Graphics g) {

    }

    @Override
    public void timer() {

    }

    @Override
    public Rectangle getPlayerOneAttackBounds() {
        return null;
    }

    @Override
    public Rectangle getPlayerTwoAttackBounds() {
        return null;
    }
}
