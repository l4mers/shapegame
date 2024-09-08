package org.example;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class UtilityTool {
    static public BufferedImage scaleImage(BufferedImage original, int width, int height){
        BufferedImage scaledImage = new BufferedImage(width, height, original.getType());
        Graphics2D g2 = scaledImage.createGraphics();
        g2.drawImage(original, 0, 0, width, height, null);
        g2.dispose();

        return scaledImage;
    }

    static public BufferedImage getSubImage(BufferedImage sprite, int col, int row, int width, int height){
        return sprite.getSubimage((col * width) - width, (row * height) - height, width, height);
    }

    static public BufferedImage loadSpriteSheet(int width, int height, String path) {
        BufferedImage img = null;
        try {
            img = UtilityTool.scaleImage(ImageIO.read(Objects.requireNonNull(UtilityTool.class.getResource(path))), width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public static BufferedImage loadUnscaledImage(String path) {
        BufferedImage img = null;
        try {
            img = ImageIO.read(Objects.requireNonNull(UtilityTool.class.getResource(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

//    static public BufferedImage loadImage(String path) {
//        BufferedImage img = null;
//        try {
//            img = UtilityTool.scaleImage(ImageIO.read(getClass().getResource(path)), width, height);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return img;
//    }
}
