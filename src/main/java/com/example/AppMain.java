/*
 * Author: Matěj Šťastný
 * Date created: 4/23/2024
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

package com.example;

import javax.swing.SwingUtilities;

/**
 * Main method for 
 * 
 */
public class AppMain {

    /////////////////
    // Constatns
    ////////////////

    public static final String APP_NAME = "TargetGame";

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
            game = new Game();
        };
        SwingUtilities.invokeLater(myApp);
    }

}
