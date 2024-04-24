/**
 * Author: Matěj Šťastný
 * Date created: 4/23/2024
 * Github link: https://github.com/kireiiiiiiii/TargetGame
 * 
 * TODO: Class header
 */

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

public class AppFrame extends JFrame {

    /**
     * Contructor to be called by {@code SwingUtilities}
     */
    public AppFrame() {
        Position windowSize = calculateWindowSize(getScreenDimensions());
        int windowSizeX = windowSize.getIntX();
        int windowSizeY = windowSize.getIntY();
        setSize(windowSizeX, windowSizeY);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Calculates the desired size of the window depening on the dimensions of the user screen. 
     * 
     * @param screenDimentions - user's screen dimensions. 
     * @return new {@code Position} object
     */
    private Position calculateWindowSize(Position screenDimentions) {
        return screenDimentions.getMultiplied(0.9);
    }

    /**
     * Gets the screen dimensions of the user's screen using {@code Toolkit}
     * 
     * @return new {@code Position} object of the dimensions
     */
    private Position getScreenDimensions() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        return new Position(screenWidth, screenHeight);
    }
}
