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

import com.kireiiiiiiii.shooting_stars.constants.Logs;

import javax.swing.SwingUtilities;

/**
 * Main method class.
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

}
