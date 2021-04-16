package com.abraham;

import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class Animate {

    public int frameRate;
    public int pos;

    private long prevTime;
    private long timer;
    private BufferedImage[] frames;

    private boolean alreadyPlayed = false;

    public Animate(int frameRate, BufferedImage[] frames){
        this.frameRate = frameRate;
        this.frames = frames;

        pos = 0;

        prevTime = System.currentTimeMillis();
    }

    public void timer(){
        timer += System.currentTimeMillis() - prevTime;

        prevTime = System.currentTimeMillis();

        if(timer > frameRate){
            pos++;
            timer = 0;

            if(pos == frames.length){
                pos = 0;

                alreadyPlayed = true;
            }
        }
    }

    public int getFrame(){
        return pos;
    }

    public BufferedImage getCurrentFrame(){
        return frames[pos];
    }

    public boolean hasBeenPlayed(){
        return alreadyPlayed;
    }

    public boolean setAlreadyPlayed(){
        return alreadyPlayed = false;
    }

}
