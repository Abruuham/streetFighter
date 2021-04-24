package com.abraham;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;
import java.awt.*;

public class GameState extends State{

    private PlayerOne p1;
    private PlayerTwo p2;

    public GameState(Main game){
        super(game);

        p1 = new PlayerOne(game, 60, 350);
        p2 = new PlayerTwo(game, 605 * 2, 350);
    }


    @Override
    public void render(Graphics g) {

        // get images for ui
        ImageIcon healthBar = new ImageIcon(Main.class.getResource("/healthBar.png"));


        // print ui for ryu
        g.setColor(Color.yellow);
        double percentBlanka = p1.getHealth() / 100.0;
        g.fillRect(450, 19, (int) (173 * percentBlanka), 11);

        // drawn last so that rect and ui could be under
        g.drawImage(healthBar.getImage(),  450, 16, (int) (healthBar.getIconWidth() * 1.2), (int) (healthBar.getIconHeight() * 1.2) ,null);


        // render instances
        p1.render(g);
        p2.render(g);


        g.setColor(Color.BLACK);


    }

    @Override
    public void timer() {

        p1.getBlankaHitBound();
        p2.getChunLiHitBound();

        p1.frame();
        p2.frame();
    }


    public Rectangle getPlayerOneHitBounds(){
        return p1.getBlankaHitBound();
    }

    public Rectangle getPlayerTwoHitBounds(){
        return p2.getChunLiHitBound();
    }
//
//    @Override
//    public Rectangle getPlayerOneAttackBounds() {
//        return null;
//    }
//
//    @Override
//    public Rectangle getPlayerTwoAttackBounds() {
//        return null;
//    }
}
