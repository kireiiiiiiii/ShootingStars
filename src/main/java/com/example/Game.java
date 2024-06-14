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

import com.example.Common.PausableTimer;
import com.example.Interface.AppFrame;
import com.example.Interface.GamePanel;
import com.example.Interface.ScreenMode;
import com.example.Interface.MenuPanel;
import com.example.Tools.ScreenUtil;

/**
 * Main game object.
 * 
 */
public class Game {

    /////////////////
    // Constatns
    ////////////////

    private static final String WINDOW_TITLE = "Target Game :3";
    private static final int GAME_LENGHT = 20;

    /////////////////
    // Variables
    ////////////////

    private AppFrame appFrame;
    private PanelType currPanel;
    private MenuPanel menuPanel;
    private GamePanel gamePanel;
    private GameLoop gameLoop;

    /////////////////
    // Game variables
    ////////////////

    private PausableTimer timer;
    private int timeRemaining;

    /////////////////
    // Constructors
    ////////////////

    public Game() {

        // Initiliaze the app frames
        double[] windowSize = ScreenUtil.getAppWindowSize();
        int windowWidth = (int) windowSize[0];
        int windowHeight = (int) windowSize[1];
        this.appFrame = new AppFrame(windowWidth, windowHeight, WINDOW_TITLE);

        // Initialize the Game loop
        this.gameLoop = new GameLoop();
        this.gameLoop.start();

        // Construct and display the menu panel
        this.menuPanel = new MenuPanel(this, appFrame);
        this.currPanel = PanelType.MENU;
        // this.appFrame.add(this.menuPanel);

        this.gamePanel = new GamePanel(this.appFrame);
        this.appFrame.add(this.gamePanel);

        onGameStart();

    }

    /////////////////
    // Events
    ////////////////

    // Called when switching from the menu panel
    public void onGameStart() {
        this.gamePanel.setScreenMode(ScreenMode.GAME);
        this.currPanel = PanelType.GAME;
        initializeTimer();
    }

    // Called on restart
    public void onGameRefresh() {

    }

    public void onGameEnd() {
        this.gamePanel.setScreenMode(ScreenMode.GAME_OVER);
        this.timer.forceStop();
    }

    public void onMouseClicked(MouseEvent e) {

    }

    public void onKeyClicked(KeyEvent e) {

        // Menu panel mode active
        if (this.currPanel == PanelType.MENU) {
            onGameStart();
        }

        // Game panel active
        else if (this.currPanel == PanelType.GAME) {

        }
    }

    /////////////////
    // Timer methods
    ////////////////

    /**
     * This method initiliazes the timer. It first sets the time left to the deafult
     * game lenght, and than it starts a timer.
     * </p>
     * On each iteration of the timer, the time left variable is changed, and the
     * time widget on the game panel is updated.
     * </p>
     * When the timer is finished, the event method {@code onGameFinished} will
     * excecute.
     * 
     */
    private void initializeTimer() {
        if (this.timer != null) {
            timer.forceStop();
        }

        this.timeRemaining = GAME_LENGHT;

        Runnable onFinished = () -> {
            onGameEnd();
        };

        Runnable everyRun = () -> {
            // this.timeRemaining = (int) this.timer.getTimeRemaining() / 1000;
            this.gamePanel.setTimeRemaining(timeRemaining);
            timeRemaining--;
        };
        this.timer = new PausableTimer(1000, GAME_LENGHT + 1, onFinished, everyRun);
        timer.start();
    }

    /////////////////
    // Threads
    ////////////////

    /**
     * Game loop {@code Thread} class.
     * 
     */
    private class GameLoop extends Thread {

        public static int deltaTime = 0;

        @Override
        public void run() {
            while (true) {
                deltaTime++;

                // 60fps rendering (each 16 miliseconds)
                // Repaints the current panel
                if (deltaTime % 16 == 0) {

                    // Render current panel
                    switch (currPanel) {
                        case GAME:
                            gamePanel.repaint();
                            break;
                        case MENU: 
                            menuPanel.repaint();
                            break;
                        default:
                            break;
                    }
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

    /////////////////
    // Enums
    ////////////////

    /**
     * Enum, that determines what {@code JPanel} is used.
     * 
     */
    public enum PanelType {
        GAME,
        MENU
    }

}
