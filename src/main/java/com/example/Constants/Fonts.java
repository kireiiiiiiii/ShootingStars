/*
 * Author: Matěj Šťastný
 * Date created: 5/16/2024
 * Github link: https://github.com/kireiiiiiiii/ShootingStars
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

/**
 * Constants class with fonts, also handeling their loading.
 * 
 */
public class Fonts {

    /////////////////
    // Deafult
    ////////////////

    public static final Font DEFAULT = new Font("Arial", Font.PLAIN, 20);

    /////////////////
    // Fonts ENGLISH
    ////////////////

    private static final Font HEADING_EN = setFont("Heading_EN.ttf");
    private static final Font TEXT_EN = setFont("Text_EN.ttf");

    /////////////////
    // Fonts CZECH
    ////////////////

    private static final Font HEADING_CZ = HEADING_EN;
    private static final Font TEXT_CZ = setFont("Text_CZ.ttf");

    /////////////////
    // Fonts JAPANESE
    ////////////////

    private static final Font HEADING_JAP = setFont("Heading_JAP.otf");
    private static final Font TEXT_JAP = setFont("Text_JAP.otf");

    /////////////////
    // Fonts KOREAN
    ////////////////

    private static final Font HEADING_KOR = setFont("Heading_KOR.ttf");
    private static final Font TEXT_KOR = DEFAULT;

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
        InputStream fontStream = getFontInputStream(fontType);
        try {
            return Font.createFont(Font.TRUETYPE_FONT, fontStream);
        } catch (FontFormatException | IOException e) {
            return DEFAULT;
        }
    }

    /////////////////
    // Accesors
    ////////////////

    public static Font heading() {
        switch (GameDialogue.getCurrentLanguage()) {
            case CZECH:
                return HEADING_CZ;
            case JAPANESE:
                return HEADING_JAP;
            case KOREAN:
                return HEADING_KOR;
            default:
                return HEADING_EN;
        }
    }

    public static Font text() {
        switch (GameDialogue.getCurrentLanguage()) {
            case CZECH:
                return TEXT_CZ;
            case JAPANESE:
                return TEXT_JAP;
            case KOREAN:
                return TEXT_KOR;
            default:
                return TEXT_EN;
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
