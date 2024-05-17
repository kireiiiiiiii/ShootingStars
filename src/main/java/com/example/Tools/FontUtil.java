/*
 * Author: Matěj Šťastný
 * Date created: TODO: Change date
 * Github link: TODO: Insert GitHub repo link, where the util file was used. 
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

package com.example.Tools;

import java.awt.Font;
import java.awt.FontMetrics;
import java.io.File;

/**
 * A utility method class for handeling {@code java/awt/Font} objects, like loading custom fonts from file, or measuring them. 
 * 
 */
public class FontUtil {

    /////////////////
    // Font file handeling methods
    ////////////////

    /**
     * Loads a {@code Font} from a {@code .ttf} of {@code .oft} font files from a
     * path given.
     * 
     * @param path - path of the font file.
     * @return A {@code Font} object reference, or {@code null} if Exception.
     */
    public static Font loadFontFromFile(String path) {
        try {
            File fontFile = new File(path);
            return Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (Exception e) {
            return null;
        }
    }

    /////////////////
    // Measurment calculating methods
    ////////////////

    /**
     * Calculates the centered position of a text in a {@code JPanel}, and return a
     * position that can be taken as input to the {@code drawString()} method.
     * 
     * @param width  - width of the rectangle that the position is being calculated
     *               from. Usually width of the {@code JPanel}.
     * @param height - height of the rectangle that the position is being calculated
     *               from. Usually height of the {@code JPanel}.
     * @param fm     - {@code FontMetric} of the target font.
     * @param text   - target text.
     * @return an {@code int} array of lenght 2, where index 0 represents {@code x}, and index 1
     *         represents {@code y} positions.
     * 
     */
    public static int[] getCenteredPos(int width, int height, FontMetrics fm, String text) {
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();
        int textX = (width - textWidth) / 2;
        int textY = (height - textHeight) / 2 + fm.getAscent();

        int[] pos = { textX, textY };
        return pos;
    }
}
