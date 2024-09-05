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

import javax.swing.SwingUtilities;

import com.kireiiiiiiii.shooting_stars.constants.Logs;

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

    @SuppressWarnings("unused")
    private static Game game;

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

}
