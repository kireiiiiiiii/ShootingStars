/*
 * Author: Matěj Šťastný
 * Date created: 6/15/2024
 * Github link: https://github.com/kireiiiiiiii/TargetGame
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package com.example.Constants;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class Textures {

    /////////////////
    // Constants
    ////////////////

    public static final Image star = setImage("Star.png");

    /////////////////
    // Private methods
    ////////////////

    /**
     * Gets the input stream of an image.
     * 
     * @param imageName - file name of the target image.
     * @return a new {@code Image} object.
     */
    private static InputStream getImageInputStream(String imageName) {
        return Textures.class.getResourceAsStream(File.separator + "Textures" + File.separator + imageName);
    }
    
    /**
     * Loads an image from the resources folder and returns it as an Image object.
     * 
     * @param imageName - file name of the image.
     * @return the loaded Image object or {@code null} if {@code IOExeption} occurs, or the {@code InputStream} is {@code null}
     */
    private static Image setImage(String imageName) {
        InputStream imageStream = getImageInputStream(imageName);
        if (imageStream == null) {
            return null;
        }
        BufferedImage img;
        try {
            img = ImageIO.read(imageStream);
        } catch (IOException e) {
            return null;
        }
        return img;
    }
    
}
