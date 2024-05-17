/*
 * Author: Matěj Šťastný
 * Date created: 5/16/2024
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

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Fonts {

    /////////////////
    // Constants
    ////////////////

    public static final Font DEFAULT = new Font("Arial", Font.PLAIN, 20);
    public static final Font HEADING = setFont("heading");

    /////////////////
    // Setter
    ////////////////

    /**
     * Sets the font from it's name using the {@code InputStream} object.
     * 
     * @param fontType - type of the font. Will be formated, so the case doesn't
     *                 matter.
     * @return loaded {@code Font} object or the default font.
     */
    private static Font setFont(String fontType) {
        fontType = fontType.substring(0, 1).toUpperCase() + fontType.substring(1).toLowerCase() + ".TTF";
        InputStream fontStream = getFontInputStream(fontType);
        try {
            return Font.createFont(Font.TRUETYPE_FONT, fontStream);
        } catch (FontFormatException | IOException e) {
            return DEFAULT;
        }
    }

    /////////////////
    // Private methods
    ////////////////

    /**
     * Get's the {@code InputStream} of a font.
     * 
     * @param fontName - file name of the font.
     * @return an {@code InputStream} object.
     */
    private static InputStream getFontInputStream(String fontName) {
        return Fonts.class.getResourceAsStream(File.separator + "Fonts" + File.separator + fontName);
    }
}
