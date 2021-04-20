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


    public static BufferedImage[] jump = new BufferedImage[7];

    public static BufferedImage[] punch = new BufferedImage[13];
    public static BufferedImage[] kick = new BufferedImage[13];

    public static void init() throws IOException{
//        Debugging to see what files were in the directory
//        File file = new File("./src/images");
//        for(String fileNames : file.list()) System.out.println(fileNames);
        SpriteSheet walkLeft = new SpriteSheet(ImageLoader.loadImage("movingFoward.png"));
        SpriteSheet stand = new SpriteSheet(ImageLoader.loadImage("standing.png"));
        SpriteSheet jumping = new SpriteSheet(ImageLoader.loadImage("jump.png"));


         // basic movement:
        for (int i = 0; i < 3; i++)
            standing[i] = stand.crop(96, 113, 96 * i , 0);


        for (int i = 0; i < 3; i++)
            walkingLeft[i] = walkLeft.crop(94, 104, 94 * i, 0);

        for (int i = 0; i < 3; i++)
            walkingRight[i] = walkLeft.crop(94, 104, 94 * i, 0);

        //crouch[0] = crouch.crop(54, 73, 0, 0);

        jump[0] = jumping.crop(71, 128, 0, 0);
        jump[1] = jumping.crop(71, 128, 0, 0);
        jump[2] = jumping.crop(71, 128, 0, 0);
        jump[3] = jumping.crop(71, 128, 0, 0);
        jump[4] = jumping.crop(71, 128, 0, 0);
        jump[5] = jumping.crop(71, 128, 0, 0);
        jump[6] = jumping.crop(71, 128, 0, 0);

//        for (int i = 3; i < 13; i++)
//            jump[i] = jumping.crop(70, 128, 70 * (i - 2), 0);
    }

}
