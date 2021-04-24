package com.abraham;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;

public class Images {
    public static BufferedImage[] standing = new BufferedImage[3];
    public static BufferedImage[] walkingLeft = new BufferedImage[3];
    public static BufferedImage[] walkingRight = new BufferedImage[3];
    public static BufferedImage[] crouch = new BufferedImage[1];


    public static BufferedImage[] jump = new BufferedImage[14];

    public static BufferedImage[] punch = new BufferedImage[13];
    public static BufferedImage[] kick = new BufferedImage[14];

    public static void init() throws IOException{

        SpriteSheet walkLeft = new SpriteSheet(ImageLoader.loadImage("movingFoward.png"));
        SpriteSheet stand = new SpriteSheet(ImageLoader.loadImage("standing.png"));
        SpriteSheet jumping = new SpriteSheet(ImageLoader.loadImage("jump.png"));
        SpriteSheet jumping1 = new SpriteSheet(ImageLoader.loadImage("jump1.png"));
        SpriteSheet jumping2 = new SpriteSheet(ImageLoader.loadImage("jump2.png"));


         // basic movement:
        for (int i = 0; i < 3; i++)
            standing[i] = stand.crop(96, 113, 96 * i , 0);


        for (int i = 0; i < 3; i++)
            walkingLeft[i] = walkLeft.crop(94, 104, 94 * i, 0);

        for (int i = 0; i < 3; i++)
            walkingRight[i] = walkLeft.crop(94, 104, 94 * i, 0);

        // Filling jump array with images
        jump[0] = jumping.crop(71, 128, 0, 0);
        jump[1] = jumping.crop(71, 128, 0, 0);
        for(int i = 2; i < 5; i++){
            jump[i] = jumping.crop(71,128,71*(i-1), 0);
;
        }
        jump[5] = jumping1.crop(93, 128, 0, 0);
        jump[6] = jumping2.crop(116, 128, 0, 0);
        jump[7] = jumping2.crop(116, 128, 0, 0);
        jump[8] = jumping1.crop(93, 128, 0, 0);
        int count = 4;
        for(int i = 9; i < 12; i++){
            jump[i] = jumping.crop(71,128,71*(count), 0);
            count--;
        }
        jump[12] = jumping.crop(71, 128, 0, 0);
        jump[13] = jumping.crop(71, 128, 0, 0);

    }

}
