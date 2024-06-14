/*
 * Author: Matěj Šťastný
 * Date created: 6/13/2024
 * Github link:  https://github.com/kireiiiiiiii/TargetGame
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

import java.awt.event.*;

import com.example.Interface.AppFrame;
import com.example.Interface.OldGamePanel;
import com.example.Interface.MenuPanel;
import com.example.Tools.ScreenUtil;
import javax.swing.JPanel;

/**
 * Main game object.
 * 
 */
public class Game {

    /////////////////
    // Constatns
    ////////////////

    private static final String APP_NAME = "TargetGame";
    private static final String WINDOW_TITLE = "Target Game :3";
   
    /////////////////
    // Variables
    ////////////////

    private AppFrame appFrame;
    private JPanel currPanel;
    private MenuPanel menuPanel;
    private OldGamePanel gamePanel;
    private GameLoop gameLoop;

    /////////////////
    // Constructors
    ////////////////
    
    public Game() {

        //Initiliaze the app frames
        double[] windowSize = ScreenUtil.getAppWindowSize();
        int windowWidth = (int) windowSize[0];
        int windowHeight = (int) windowSize[1];
        this.appFrame = new AppFrame(windowWidth, windowHeight, WINDOW_TITLE);

        // Initialize the Game loop
        this.gameLoop = new GameLoop(); 
        this.gameLoop.start();

        // Construct and display the menu panel
        this.menuPanel = new MenuPanel(this, appFrame);
        this.currPanel = this.menuPanel;
        this.appFrame.add(this.menuPanel);

        // Timer start
        // Load the top score
    }

    /////////////////
    // Events
    ////////////////

    // Called when switching from the menu panel
    public void onGameStart() {

    }

    // Called on restart
    public void onGameRefresh() {
        
    }

    public void onGameEnd() {
        // Switch the Panel 
    }

    public void onMouseClicked(MouseEvent e) {

    }

    public void onKeyClicked(KeyEvent e) {

        // Menu panel mode active
        if (this.currPanel == this.menuPanel) {
            onGameStart();
            this.currPanel = this.gamePanel;
        }

        // Game panel active
        else if (this.currPanel == this.gamePanel) {

        }
    }

    /**
     * Game loop {@code Thread} class.
     * 
     */
    private class GameLoop extends Thread {

        public static int deltaTime = 0;

        @Override
        public void run() {
            while (true) {
                deltaTime ++;
            
                // 60fps rendering (each 16 miliseconds)
                // Repaints the current panel
                if (deltaTime % 16 == 0) {
                    currPanel.repaint();
                }

                // Sleep for 1 Milisecond
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
