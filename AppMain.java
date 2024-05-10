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

import javax.swing.SwingUtilities;
import GUI.*;

/**
 * TODO: Class header. 
 * 
 */
public class AppMain {

    /////////////////
    // Main method
    ////////////////
    
    public static void main(String[] args) {
        Runnable myApp = () -> new AppFrame(); // Instance of my JFrame App
        SwingUtilities.invokeLater(myApp);
    }
}
