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

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.SwingUtilities;

import Interface.*;

/**
 * TODO: Class header.
 * 
 */
public class AppMain {

    /////////////////
    // Constatns
    ////////////////

    private static final String WINDOW_TITLE = "Target Game :3";

    /////////////////
    // Variables
    ////////////////

    @SuppressWarnings("unused")
    private static AppFrame appFrame;

    /////////////////
    // Main method
    ////////////////

    public static void main(String[] args) {
        Runnable myApp = () -> {
            double[] windowSize = getAppWindowSize(getScreenDimensions());
            int width = (int) windowSize[0];
            int height = (int) windowSize[1];
            appFrame = new AppFrame(width, height, WINDOW_TITLE);
        };
        SwingUtilities.invokeLater(myApp);
    }

    /////////////////
    // Private methods
    ////////////////

    /**
     * Calculates the desired size of the window depening on the dimensions of the
     * user screen.
     * 
     * @param dimentions - user's screen dimensions.
     * @return {@code double} position array.
     */
    private static double[] getAppWindowSize(int[] screenDimentions) {
        double x = screenDimentions[0] * 0.9;
        double y = screenDimentions[1] * 0.9;
        double[] dimensions = { x, y };
        return dimensions;
    }

    /**
     * Gets the screen dimensions of the user's screen using {@code Toolkit}.
     * 
     * @return new {@code Position} object of the dimensions.
     */
    private static int[] getScreenDimensions() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int[] dimensions = { screenWidth, screenHeight };
        return dimensions;
    }
}
