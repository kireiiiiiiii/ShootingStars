/**
 * Author: Matěj Šťastný
 * Date created: 4/23/2024
 * Github link: https://github.com/kireiiiiiiii/TargetGame
 * 
 * TODO: Class header
 */

import javax.swing.SwingUtilities;

import GUI.*;

public class AppMain {
    public static void main(String[] args) {
        Runnable myApp = () -> new AppFrame(); // Instance of my JFrame App
        SwingUtilities.invokeLater(myApp);
    }
}
