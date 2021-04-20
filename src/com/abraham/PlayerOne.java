package com.abraham;

import java.awt.*;
import java.awt.image.BufferedImage;


public class PlayerOne extends Player{


    private int health;
    private int velocityX, velocityY;
    private Main game;
    private boolean[] animations = new boolean[20];

    private final String movements[] = {"Standing", "Walk_Right", "Walk_Left", "Crouching", "Jumping", "Front_Flip", "Back_Flip"};
    private final String attacks[] = {"Attack_V", "Attack_B", "Crouch_Attack"};

    private final int GRAVITY = 1;

    // basic movement
    private final int Standing         = 0;
    private final int Walking_Right     = 1;
    private final int Walking_Left     = 2;
    private final int CROUCHING      = 3;
    private final int Jumping        = 9;


    // dummy var for checks
    private final int DUMMY = 19;


    private Animate standing;
    private Animate walkLeft;
    private Animate walkRight;
    private Animate jump;


    private final int JUMP_SPEED = -10;



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
        System.out.println(x);



        standing = new Animate(110, Images.standing);
        walkLeft = new Animate(100, Images.walkingLeft);
        walkRight = new Animate(110, Images.walkingRight);
        jump = new Animate(60, Images.jump);

    }


    public void checkCollision(){
        // check if the enemies hit box has intersected with the current players hit box
//        if(game.getGameState().getPlayerOneAttackBounds().intersects(getHitBounds())){
//
//        }
    }
    public void checkWalls(){
        // check to see if player is at a edge of the screen
        if(x < 0){
            x = 0;
        }
        // check to see if the player is at the d edge of the screen
        if(x > Main.WINDOW_WIDTH  * 2){
            x = Main.WINDOW_WIDTH  * 2;
        }
    }


    public int getHealth(){
        return health;
    }

//    public Rectangle getHitBounds(){
//        return 0;
//    }


    @Override
    public void frame() {

        standing.timer();
        jump.timer();

        walkLeft.timer();
        walkRight.timer();


        if (y == 275) {

            // if press d, move forward
            if (game.getKey().d ) {
                velocityX = 2;
                // reset and init true to state
                handleAnimation(Walking_Right);
            } else if(game.getKey().a ){
                velocityX = -2;
                // reset and init true to state
                handleAnimation(Walking_Left);
            } else if (game.getKey().w) {
                velocityY = JUMP_SPEED - 2;
                y-=1;
                jump.pos = 0;
                handleAnimation(Jumping);
            } else {

                velocityX = 0;
                velocityY = 0;

                // reset animations that are instantaneous (only activate when pressed)
                animations[CROUCHING] = false;
                animations[Walking_Left] = false;
                animations[Walking_Right] = false;
                animations[Jumping] = false;

                // if only dummy boolean is active, then idle
                if (animations[DUMMY]) {
                    handleAnimation(Standing);
                }

            }
            // otherwise, player is in air
        }

        // wdate horizontal pos.
        x += velocityX;



        if(y < 275){
            fall();
        }
        // if player clips through floor, set y to floor
        if (y > 275)
            y = 275;

        // check edges of screen
        checkWalls();
    }

    public void fall(){
        velocityY += GRAVITY;

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
        if (animations[Walking_Right]) {
            return walkRight.getCurrentFrame();
        } else if(animations[Walking_Left]){
            return walkLeft.getCurrentFrame();
        } else if(animations[Jumping]){
            return jump.getCurrentFrame();
        }
        else return standing.getCurrentFrame();

    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(new Color(0,0,0, 150));
        g.fillOval((int) (x), 188 * Main.SCALE, 80, 16);

        if (animations[Walking_Right]) {
            g.drawImage(getCurrentAnimFrame(), (int) (x - 5), (int) (y + 10), null);
        } else if (animations[Walking_Left]) {
            g.drawImage(getCurrentAnimFrame(), (int) (x - 5), (int) (y + 10), null);
        } else if (animations[Jumping]){
            g.drawImage(getCurrentAnimFrame(), (int) x - 5, (int) y - 30, null);
        } else {
            g.drawImage(getCurrentAnimFrame(), (int) x, (int) y , null);
        }
    }
}
