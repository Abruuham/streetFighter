package com.abraham;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.Buffer;

public class Images {
    public static BufferedImage[] standing = new BufferedImage[3];
    public static BufferedImage[] walkingLeft = new BufferedImage[3];
    public static BufferedImage[] walkingRight = new BufferedImage[3];
    public static BufferedImage[] crouch = new BufferedImage[1];


    public static BufferedImage[] jump = new BufferedImage[13];

    public static BufferedImage[] punch = new BufferedImage[13];
    public static BufferedImage[] kick = new BufferedImage[13];

    public static void init() throws IOException{
        SpriteSheet walkLeft = new SpriteSheet(ImageLoader.loadImage("/images/movingForward.png"));
        SpriteSheet stand = new SpriteSheet(ImageLoader.loadImage("/images/standing.png"));
        SpriteSheet jumping = new SpriteSheet(ImageLoader.loadImage("/images/jump.png"));


        // basic movement:
        for (int i = 0; i < 3; i++)
            standing[i] = stand.crop(57, 106, 57 * i, 0);

        for (int i = 0; i < 3; i++)
            walkingLeft[i] = walkLeft.crop(70, 110, 70 * i, 0);

        for (int i = 0; i < 3; i++)
            walkingRight[i] = walkLeft.crop(70, 108, 70 * i, 0);

        //crouch[0] = crouch.crop(54, 73, 0, 0);

        jump[0] = jumping.crop(70, 154, 0, 0);
        jump[1] = jumping.crop(70, 154, 0, 0);
        jump[2] = jumping.crop(70, 154, 0, 0);

        for (int i = 3; i < 13; i++)
            jump[i] = jumping.crop(70, 154, 70 * (i - 2), 0);
    }

}
