/*
 * Author: Matěj Šťastný
 * Date created: 6/13/2024
 * Github link:  https://github.com/kireiiiiiiii/ShootingStars
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
import com.example.Common.Settings;
import com.example.Common.Vec2D;
import com.example.Constants.Files;
import com.example.Interface.AppFrame;
import com.example.Interface.GamePanel;
import com.example.Interface.GameScreenMode;
import com.example.Interface.MenuPanel;
import com.example.Tools.ScreenUtil;
import com.example.Constants.GameDialogue;
import com.example.Constants.Keybinds;
import com.example.Constants.Logs;

/**
 * Main game object.
 * 
 */
public class Game {

    /////////////////
    // Constatns
    ////////////////

    private static final int GAME_LENGHT = 20;
    private static final int DEFAULT_TARGET_RADIUS = 20;
    private static final int TARGET_SCORE = 10;

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
    private int targetRadius;
    private int score;

    /////////////////
    // Constructors
    ////////////////

    public Game() {

        // Initiliaze the app frames
        double[] windowSize = ScreenUtil.getAppWindowSize();
        int windowWidth = (int) windowSize[0];
        int windowHeight = (int) windowSize[1];
        this.appFrame = new AppFrame(windowWidth, windowHeight, GameDialogue.appTitle());

        // Set up the scores variable
        onTopscoreFileLoad();

        // Load the config file
        Settings.getLanguage();

        // Construct the game panel
        this.gamePanel = new GamePanel(this.appFrame, this);

        // Construct and display the menu panel
        this.menuPanel = new MenuPanel(this, appFrame);
        this.currPanel = PanelType.MENU;
        this.appFrame.add(this.menuPanel);

        // Initialize the Game loop
        this.gameLoop = new GameLoop();
        this.gameLoop.start();

    }

    /////////////////
    // Events
    ////////////////

    // Called when the current JPanel changes from game to menu
    public void onGoToMenu() {
        this.appFrame.remove(this.gamePanel);
        this.appFrame.add(this.menuPanel);
        this.appFrame.revalidate();
        this.appFrame.repaint();
        this.menuPanel.requestFocusInWindow();
        this.currPanel = PanelType.MENU;
    }

    // Called when switching from the menu panel
    public void onGameStart() {
        Logs.log(Logs.GAME_START);
        this.appFrame.remove(this.menuPanel);
        this.appFrame.add(this.gamePanel);
        this.appFrame.revalidate();
        this.appFrame.repaint();
        this.gamePanel.requestFocusInWindow();
        this.gamePanel.setTopscoreWidget(this.topScore.get());
        this.gamePanel.setScreenMode(GameScreenMode.GAME);
        this.currPanel = PanelType.GAME;
        this.targetRadius = DEFAULT_TARGET_RADIUS;
        this.gamePanel.setTargetWidget(this.targetRadius);
        this.score = 0;
        initializeTimer();
        onTargetClicked(true);
    }

    // Called on restart
    public void onGameRestart() {
        Logs.log(Logs.GAME_RESTART);
        this.gamePanel.setTopscoreWidget(this.topScore.get());
        this.gamePanel.setScreenMode(GameScreenMode.GAME);
        this.timer.forceStop();
        this.score = 0;
        this.gamePanel.setScore(this.score);
        onTargetClicked(true);
        initializeTimer();
    }

    public void onGamePause() {
        Logs.log(Logs.GAME_PAUSE);
        this.gamePanel.setScreenMode(GameScreenMode.PAUSE);
        this.timer.pause();
    }

    public void onGameResumed() {
        Logs.log(Logs.GAME_RESUMED);
        this.gamePanel.setScreenMode(GameScreenMode.GAME);
        this.timer.resume();
    }

    public void onGameEnd() {
        Logs.log(Logs.GAME_OVER);
        this.gamePanel.setScreenMode(GameScreenMode.GAME_OVER);
        this.gamePanel.setGameOverScreen(this.topScore.get(), this.score);
        this.timer.forceStop();
        // New highscore
        if (this.score > this.topScore.get()) {
            this.topScore.set(this.score);
            onTopscoreFileSave();
        }
        onTopscoreFileLoad();
    }

    public void onTimerIteration() {
        Logs.log(Logs.TIMER_INTEARION);
        this.gamePanel.setTimeRemaining(timeRemaining);
        this.timeRemaining--;
    }

    public void onTargetClicked(boolean init) {
        int maxX = this.gamePanel.getWidth() - this.targetRadius * 2 - this.gamePanel.WINDOW_PADDING * 2;
        int maxY = this.gamePanel.getHeight() - this.targetRadius * 2 - this.gamePanel.WINDOW_PADDING * 2;
        Vec2D newPos = new Vec2D();
        newPos.randomize(maxX, maxY);

        int x = newPos.getIntX();
        int y = newPos.getIntY();
        int[] pos = { x, y };

        this.gamePanel.setTargetWidget(pos);

        // Update the score and log the click, if not ititial execution
        if (!init) {
            Logs.log(Logs.TAGRET_HIT);
            this.score += TARGET_SCORE;
            this.gamePanel.setScore(this.score);
        }
    }

    public void onTargetMisclicked() {
        Logs.log(Logs.TAGRET_NOT_HIT);
        this.score = Math.max(0, this.score - TARGET_SCORE);
        this.gamePanel.setScore(this.score);
    }

    public void onTopscoreFileLoad() {
        Logs.log(Logs.TOPSCORE_FILE_LOAD);
        this.topScore = new AdvancedVariable<Integer>(Files.TOP_SCORE_FILE);
        try {
            this.topScore.loadFromFile(Integer.class);
        } catch (IOException e) {
            this.topScore.set(0);
        }
        // The JSON file is empty = first time playing
        if (this.topScore.get() == null) {
            this.topScore.set(0);
        }
    }

    public void onTopscoreFileSave() {
        Logs.log(Logs.TOPSCORE_FILE_SAVED);
        try {
            this.topScore.save();
        } catch (IOException e) {
            System.out.println("FATAL - Could not save Topscore file");
        }
    }

    /////////////////
    // Mouse events override methods
    ////////////////

    public void onMouseClicked(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e, PanelType p) {

    }

    public void mouseMoved(MouseEvent e, PanelType p) {

    }

    public void mouseClicked(MouseEvent e, PanelType p) {

    }

    public void mousePressed(MouseEvent e, PanelType p) {

    }

    public void mouseReleased(MouseEvent e, PanelType p) {
        switch (p) {
            case MENU:
                // onGameStart();
                break;

            case GAME:
                if (this.gamePanel.getScreenMode() == GameScreenMode.GAME) {
                    if (this.gamePanel.isTargetClicked(e)) {
                        onTargetClicked(false);
                    } else {
                        onTargetMisclicked();
                    }
                } else if (this.gamePanel.getScreenMode() == GameScreenMode.PAUSE
                        || this.gamePanel.getScreenMode() == GameScreenMode.GAME_OVER) {
                    if (this.gamePanel.wasHomeButtonClicked(e)) {
                        onGoToMenu();
                    }
                }
                break;

            default:
                break;
        }
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
                        if (this.gamePanel.getScreenMode() == GameScreenMode.PAUSE) {
                            onGameResumed();
                        } else if (this.gamePanel.getScreenMode() == GameScreenMode.GAME) {
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
            onTimerIteration();
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

                    if (currPanel != null) {
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
