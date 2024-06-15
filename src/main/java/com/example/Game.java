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
import java.io.IOException;

import com.example.Common.AdvancedVariable;
import com.example.Common.PausableTimer;
import com.example.Constants.Paths;
import com.example.Interface.AppFrame;
import com.example.Interface.GamePanel;
import com.example.Interface.ScreenMode;
import com.example.Interface.MenuPanel;
import com.example.Tools.ScreenUtil;
import com.example.Constants.Keybinds;

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
    private AdvancedVariable<Integer> topScore;

    /////////////////
    // Game variables
    ////////////////

    private PausableTimer timer;
    private int timeRemaining;
    private boolean isPaused;

    /////////////////
    // Constructors
    ////////////////

    public Game() {

        // Initiliaze the app frames
        double[] windowSize = ScreenUtil.getAppWindowSize();
        int windowWidth = (int) windowSize[0];
        int windowHeight = (int) windowSize[1];
        this.appFrame = new AppFrame(windowWidth, windowHeight, WINDOW_TITLE);

        // Set up the scores variable
        this.topScore = new AdvancedVariable<Integer>(Paths.TOP_SCORE_FILE);
        try {
            this.topScore.loadFromFile(Integer.class);
        } catch (IOException e) {
            this.topScore.set(0);
        }
        // The JSON file is empty = first time playing
        if (this.topScore.get() == null) {
            this.topScore.set(0);
        }
        System.out.println("Top score: " + this.topScore.get());


        // Initialize the Game loop
        this.gameLoop = new GameLoop();
        this.gameLoop.start();

        // Construct and display the menu panel
        this.menuPanel = new MenuPanel(this, appFrame);
        this.currPanel = PanelType.MENU;
        // this.appFrame.add(this.menuPanel);

        this.gamePanel = new GamePanel(this.appFrame, this);
        this.appFrame.add(this.gamePanel);

        onGameStart();

    }

    /////////////////
    // Events
    ////////////////

    // Called when switching from the menu panel
    public void onGameStart() {
        this.isPaused = false;
        this.gamePanel.setScreenMode(ScreenMode.GAME);
        this.currPanel = PanelType.GAME;
        this.gamePanel.setTopscoreWidget(this.topScore.get());
        initializeTimer();
    }

    // Called on restart
    public void onGameRestart() {
        this.timer.forceStop();
        this.timer.start();
    }

    public void onGamePause() {
        this.isPaused = true;
        this.gamePanel.setScreenMode(ScreenMode.PAUSE);
        this.timer.pause();
    }

    public void onGameResumed() {
        this.isPaused = false;
        this.gamePanel.setScreenMode(ScreenMode.GAME);
        this.timer.resume();
    }

    public void onGameEnd() {
        this.gamePanel.setScreenMode(ScreenMode.GAME_OVER);
        this.timer.forceStop();
    }

    public void onMouseClicked(MouseEvent e) {

    }

    /////////////////
    // Mouse events override methods
    ////////////////

    public void mouseDragged(MouseEvent e, PanelType p) {

    }

    public void mouseMoved(MouseEvent e, PanelType p) {

    }

    public void mouseClicked(MouseEvent e, PanelType p) {

    }

    public void mousePressed(MouseEvent e, PanelType p) {

    }

    public void mouseReleased(MouseEvent e, PanelType p) {

    }

    public void mouseEntered(MouseEvent e, PanelType p) {

    }

    public void mouseExited(MouseEvent e, PanelType p) {

    }

    /////////////////
    // Key events override methods
    ////////////////

    public void keyPressed(KeyEvent e, PanelType p) {
        switch (p) {
            case GAME:
                
                switch (e.getKeyCode()) {
                    case Keybinds.DEBUGG_KEY:
                        
                        break;
                    case Keybinds.PAUSE_KEY:
                        if(isPaused) {
                            onGameResumed();
                        }
                        else {
                            onGamePause();
                        }
                        break;
                    case Keybinds.RESTART_KEY:
                        onGameRestart();
                        break;
                    default:
                        break;
                }

                break;
        
            case MENU:
                onGameStart();
                break;

            default:
                break;
        }
    }

    public void keyReleased(KeyEvent e, PanelType p) {

    }

    public void keyTyped(KeyEvent e, PanelType p) {

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
