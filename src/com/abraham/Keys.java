package com.abraham;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keys implements KeyListener {

    private boolean[] keys;

    // movement keys and attack keys for player one
    public boolean w, a, s, d, v, b;

    public boolean u,h,j,k;

    // Default constructor
    public Keys(){
        keys = new boolean[256];
    }

    public void move(){
        w = keys[KeyEvent.VK_W];
        a = keys[KeyEvent.VK_A];
        s = keys[KeyEvent.VK_S];
        d = keys[KeyEvent.VK_D];
        v = keys[KeyEvent.VK_V];
        b = keys[KeyEvent.VK_B];


        u = keys[KeyEvent.VK_U];
        h = keys[KeyEvent.VK_H];
        j = keys[KeyEvent.VK_J];
        k = keys[KeyEvent.VK_K];


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        keys[e.getKeyCode()] = true;

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == 68){
            keys[e.getKeyCode()] = false;
        } else if(e.getKeyCode() == 65){
            keys[e.getKeyCode()] = false;
        } else if(e.getKeyCode() == 87){
            keys[e.getKeyCode()] = false;
        } else if(e.getKeyCode() == 85){
            keys[e.getKeyCode()] = false;
        } else if(e.getKeyCode() == 72){
            keys[e.getKeyCode()] = false;
        } else if(e.getKeyCode() == 75){
            keys[e.getKeyCode()] = false;
        }

    }
}
