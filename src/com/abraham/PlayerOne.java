package com.abraham;

import java.awt.*;
import java.util.Random;

public class PlayerOne extends Player{


    private int health;
    private int veloctyX, veloctyY;
    private Main game;
    private boolean[] images = new boolean[20];

    private final String movements[] = {"Standing", "Walk_Right", "Walk_Left", "Crouching", "Jumping", "Front_Flip", "Back_Flip"};
    private final String attacks[] = {"Attack_V", "Attack_B", "Crouch_Attack"};

    private final int VELOCITY = 2;
    private final int JUMP_ACCEL = -10;
    private final int GRAVITY = 1;

    private Animate standing;
    private Animate walkLeft;
    private Animate walkRight;
    private Animate crouch;
    private Animate jump;
    private Animate attackV, attackB;

    private boolean isHurt;
    private long prevTime;


    Random rand;

    /**
     * Default constructor for player one
     * @param game
     * @param x
     * @param y
     */
    public PlayerOne(Main game, float x, float y) {
        super(x, y);

        this.game = game;

        health = 100;

        rand = new Random();


        standing = new Animate(100, Images.standing);
        walkLeft = new Animate(100, Images.walkingLeft);
        walkRight = new Animate(100, Images.walkingRight);
        jump = new Animate(85, Images.jump);

    }


    public void checkCollision(){
        // check if the enemies hit box has intersected with the current players hit box
        if(game.getGameState().getPlayerOneAttackBounds().intersects(getHitBounds())){

        }
    }
    public void checkWalls(){
        // check to see if player is at left edge of the screen
        if(x < 0){
            x = 0;
        }
        // check to see if the player is at the right edge of the screen
        if(x > Main.WIDTH * 2){
            x = Main.WIDTH * 2;
        }
    }


    public int getHealth(){
        return health;
    }

    public Rectangle getHitBounds(){
        return 0;
    }


    @Override
    public void frame() {

        standing.timer();
        jump.timer();

        walkLeft.timer();
        walkRight.timer();

        if(y == 280){

        }
    }

    @Override
    public void render(Graphics g) {

    }
}
