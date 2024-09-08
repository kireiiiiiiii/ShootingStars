/*
 * Author: Matěj Šťastný
 * Date created: 4/23/2024
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

package com.kireiiiiiiii.shooting_stars;

import java.util.ArrayList;

import javax.swing.SwingUtilities;

import com.kireiiiiiiii.shooting_stars.constants.GameDialogue;
import com.kireiiiiiiii.shooting_stars.constants.Logs;
import com.kireiiiiiiii.shooting_stars.tools.SpreadsheetUtil;

/**
 * Main method for
 * 
 */
public class AppMain {

    /////////////////
    // Constatns
    ////////////////

    public static final String APP_NAME = "ShootingStars";

    /////////////////
    // Variables
    ////////////////

    public static Game game;

    /////////////////
    // Main method
    ////////////////

    public static void main(String[] args) {
        Runnable myApp = () -> {
            Logs.log(Logs.APP_START);
            game = new Game();
        };
        SwingUtilities.invokeLater(myApp);
    }

    // ! DEBUG MAIN METHOD
    // public static void main(String[] args) {
    // System.out.println(GameDialogue.getLanguages());
    // }

}
