package com.abraham;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;
import java.awt.*;

public class GameState extends State{

    private PlayerOne p1;
    //private PlayerTwo p2;

    public GameState(Main game){
        super(game);

        p1 = new PlayerOne(game, 60, 350);
    }


    @Override
    public void render(Graphics g) {

        // get images for ui
        ImageIcon healthBar = new ImageIcon(Main.class.getResource("/healthBar.png"));


        // print ui for ryu
        g.setColor(Color.yellow);
        double percentRyu = p1.getHealth() / 100.0;
        g.fillRect(208, 19, (int) (173 * percentRyu), 11);

        // drawn last so that rect and ui could be under
        g.drawImage(healthBar.getImage(),  207, 16, (int) (healthBar.getIconWidth() * 1.2), (int) (healthBar.getIconHeight() * 1.2) ,null);


        // render instances
        p1.render(g);


        g.setColor(Color.BLACK);


    }

    @Override
    public void timer() {
        p1.frame();
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
