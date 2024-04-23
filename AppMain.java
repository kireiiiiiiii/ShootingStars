/**
 * Author: Matěj Šťastný
 * Date created: 4/23/2024
 * 
 * TODO: Class header
 */

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class AppMain extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AppMain()); //TODO what does this do?
    }

    public AppMain() {
        setVisible(true);
    }
}
