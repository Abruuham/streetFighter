package com.abraham;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PlayerTwo extends Player{


    private int health;
    private int velocityX, velocityY;
    private Main game;
    private boolean[] animations = new boolean[20];
    private boolean hit;
    private long prevTime;

    // Array of basic movements that Player One can perform
    private final String movements[] = {"Standing", "Walking_Right", "Walking_Left", "crouching", "Jumping", "Hit"};

    // Array of basic attacks that Player One can perform
    private final String attacks[] = {"Attack_V", "Attack_B", "Crouch_Attack"};

    private final int GRAVITY = 1;



    private final int MAX_SPEED = 2;


    // dummy var for checks
    private final int DUMMY = 19;


    private Animate standing;
    private Animate walkLeft;
    private Animate walkRight;
    private Animate jump;


    private final int JUMP_SPEED = -18;



    /**
     * Default constructor for player one
     * @param game
     * @param x
     * @param y
     */
    public PlayerTwo(Main game, float x, float y) {
        super(x, y);

        this.game = game;

        health = 100;

        // Sets the frame rate of the the animations, the higher the frame rate the slower it will be cycled through
        standing = new Animate(110, Images.standing);
        walkLeft = new Animate(100, Images.walkingLeft);
        walkRight = new Animate(110, Images.walkingRight);
        jump = new Animate(75, Images.jump);

    }


    public void checkCollision(){
        // check if the enemies hit box has intersected with the current players hit box
        if(game.getGameState().getPlayerOneHitBounds().intersects(getChunLiHitBound())){

            // if not already hit
            if (!hit) {
                handleAnimation(getMovement("Hit"));
                prevTime = System.currentTimeMillis();
                hit = true;
                health-=5;
            }

            try {
                TimeUnit.MILLISECONDS.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    public void checkWalls(){
        // check to see if player is at a edge of the screen
        if(x < 0){
            x = 0;
        }
        // check to see if the player is at the d edge of the screen
        if(x > Main.WINDOW_WIDTH ){
            x = Main.WINDOW_WIDTH ;
        }
    }


    public int getHealth(){
        return health;
    }

    public Rectangle getChunLiHitBound(){
        return new Rectangle((int)x, (int)y, getCurrentAnimFrame().getWidth() + 80, getCurrentAnimFrame().getHeight() + 85);
    }


    @Override
    public void frame() {

        standing.timer();
        jump.timer();

        walkLeft.timer();
        walkRight.timer();


        if (y == 350) {
            // if press d, move forward
            if (game.getKey().k ) {
                velocityX = 2;
                // reset and init true to state
                handleAnimation(getMovement("Walking_Right"));
            } else if(game.getKey().h ){
                velocityX = -2;
                // reset and init true to state
                handleAnimation(getMovement("Walking_Left"));
            } else if (game.getKey().u) {
                velocityY = JUMP_SPEED - 2;
                y-=1;
                jump.pos = 0;
                handleAnimation(getMovement("Jumping"));
            } else {

                velocityX = 0;
                velocityY = 0;

                // reset animations that are instantaneous (only activate when pressed)
                animations[getMovement("crouching")] = false;
                animations[getMovement("Walking_Left")] = false;
                animations[getMovement("Walking_Right")] = false;
                animations[getMovement("Jumping")] = false;

                // if only dummy boolean is active, then idle
                if (animations[DUMMY]) {
                    handleAnimation(Arrays.asList(movements).indexOf("Standing"));
                }

            }
            // otherwise, player is in air
        }

        checkCollision();

        // update horizontal pos.
        x += velocityX;

        if(y < 350){
            fall();
        }
        // if player clips through floor, set y to floor
        if (y > 350)
            y = 350;

        // check edges of screen
        checkWalls();
    }

    public void fall(){
        velocityY += GRAVITY;

        // if greater than max speed, just equal max speed
        if (velocityY > MAX_SPEED){
            velocityY = MAX_SPEED + 3;
        }

        // if horizontally still...
        if (velocityX == 0) {
            // jump anims
            if (jump.getFrame() >= 3 && jump.getFrame() <= 4) {
                velocityY = 0;
            }
        }

        y += velocityY;


    }

    public void handleAnimation(int unchanged){

        // make all false...
        for (int i = 0; i < 20; i++) {
            animations[i] = false;
        }

        // except active anim
        animations[unchanged] = true;

    }


    private BufferedImage getCurrentAnimFrame() {
        if (animations[getMovement("Walking_Right")]) {
            return walkRight.getCurrentFrame();
        } else if(animations[getMovement("Walking_Left")]){
            return walkLeft.getCurrentFrame();
        } else if(animations[getMovement("Jumping")]){
            return jump.getCurrentFrame();
        }
        else return standing.getCurrentFrame();

    }

    private int getMovement(String str){
        return Arrays.asList(movements).indexOf(str);
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(new Color(0,0,0, 150));
        g.fillOval((int) (x), 260 * Main.SCALE, 160, 16);
        Random rand = new Random();

        if (hit) {
            int k = rand.nextInt(3);
            g2d.translate(-k, k);
        }


        if (animations[getMovement("Walking_Right")]) {

            g.drawImage(getCurrentAnimFrame(), (int) x , (int) y, getCurrentAnimFrame().getWidth() * 2, getCurrentAnimFrame().getHeight() * 2,null);
        } else if (animations[getMovement("Walking_Left")]) {
            g.drawImage(getCurrentAnimFrame(), (int) x , (int) y, getCurrentAnimFrame().getWidth() * 2, getCurrentAnimFrame().getHeight() * 2,null);
        } else if (animations[getMovement("Jumping")]){
            g.drawImage(getCurrentAnimFrame(), (int) x - 5, (int) y - 25, getCurrentAnimFrame().getWidth() * 2, getCurrentAnimFrame().getHeight() * 2,null);
        } else {
            g.drawImage(getCurrentAnimFrame(), (int) x, (int) y , getCurrentAnimFrame().getWidth() * 2, getCurrentAnimFrame().getWidth() * 2,null);
        }
    }
}
