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

package Constants;

import java.awt.Font;

import Tools.*;

public class Fonts {

    /////////////////
    // Constants
    ////////////////

    public static final Font DEFAULT = new Font("Arial", Font.PLAIN, 20);
    public static final Font HEADING = setFont("heading");

    /////////////////
    // Setter
    ////////////////

    private static Font setFont(String fontType) {
        fontType = fontType.substring(0, 1).toUpperCase() + fontType.substring(1).toLowerCase();
        Font font = FontUtil.loadFontFromFile(Paths.FONT_DIR + "\\" + fontType + ".TTF");
        if (font == null) {
            return DEFAULT;
        }
        return font;
    }

} 
