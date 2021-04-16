package com.abraham;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageLoader {

    /**
     * @param path
     * 		file location of image
     * @return
     * 		returns a BufferedImage
     */
    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(ImageLoader.class.getResource(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
