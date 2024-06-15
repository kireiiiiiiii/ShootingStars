/*
 * Author: Matěj Šťastný
 * Date created: 4/23/2024
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

package com.example.Interface;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import com.example.Game;
import com.example.Game.PanelType;
import com.example.Interface.GamePanelElements.GameOverScreen;
import com.example.Interface.GamePanelElements.PauseScreen;
import com.example.Interface.GamePanelElements.ScoreWidget;
import com.example.Interface.GamePanelElements.TargetWidget;
import com.example.Interface.GamePanelElements.TimerWidget;
import com.example.Interface.GamePanelElements.TopscoreWidget;

import java.awt.*;

/**
 * TODO: Make class header
 * 
 */
public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

    /////////////////
    // Constants
    ////////////////

    public final int WINDOW_PADDING = 10;

    /////////////////
    // Class variables
    ////////////////

    public ScreenMode mode;
    private JFrame owner;
    private Game game;

    /////////////////
    // Renderables
    ////////////////

    private ArrayList<Renderable> gameElements;
    private ArrayList<Renderable> pauseElements;
    private ArrayList<Renderable> gameOverElements;
    private ScoreWidget scoreWidget;
    private TopscoreWidget topscoreWidget;
    private TimerWidget timerWidget;
    private TargetWidget target;

    /////////////////
    // Constructors
    ////////////////

    /**
     * Standart constructor
     * 
     * @param owner - {JFrame} object, that owns this {@code JPanel}.
     */
    public GamePanel(JFrame owner, Game game) {
        this.owner = owner;
        this.game = game;
        addMouseListener(this);
        addMouseMotionListener(this);
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();

        // Initialize the ArrayLists
        this.gameElements = new ArrayList<Renderable>();
        this.pauseElements = new ArrayList<Renderable>();
        this.gameOverElements = new ArrayList<Renderable>();

        // // Set up the scores variable
        // this.topScore = new AdvancedVariable<Integer>(Paths.TOP_SCORE_FILE);
        // try {
        //     this.topScore.loadFromFile(Integer.class);
        // } catch (IOException e) {
        //     this.topScore.set(0);
        // }
        // // The JSON file is empty = first time playing
        // if (this.topScore.get() == null) {
        //     this.topScore.set(0);
        // }
        // System.out.println("Top score: " + this.topScore.get());

        // Set deafult variable values
        this.mode = ScreenMode.GAME;

        // Set widgets
        setGameWidgets();
        setPauseWidgets();
        setGameOverWidget();

    }

    /////////////////
    // JPanel override methods
    ////////////////

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;
        ArrayList<Renderable> currRender;

        switch (this.mode) {
            case GAME:
                currRender = this.gameElements;
                break;
            case PAUSE:
                currRender = this.pauseElements;
                break;
            case GAME_OVER:
                currRender = this.gameOverElements;
                break;
            default:
                currRender = new ArrayList<Renderable>();
                break;
        }

        for (Renderable r : currRender) {
            r.refresh(g);
        }

    }

    /*
     * Size methods return the measurments of the owning {@JFrame} object.
     * Methods return zero, if owner wasn't initialized (is {@code null}).
     */

    @Override
    public int getWidth() {
        return this.owner == null ? 0 : this.owner.getWidth();
    }

    @Override
    public int getHeight() {
        return this.owner == null ? 0 : this.owner.getHeight();
    }

    @Override
    public Dimension getSize() {
        return new Dimension(getWidth(), getHeight());
    }

    /////////////////
    // Mouse events override methods
    ////////////////

    @Override
    public void mouseDragged(MouseEvent e) {
        this.game.mouseDragged(e, PanelType.GAME);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.game.mouseMoved(e, PanelType.GAME);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.game.mouseClicked(e, PanelType.GAME);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.game.mousePressed(e, PanelType.GAME);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.game.mouseReleased(e, PanelType.GAME);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.game.mouseEntered(e, PanelType.GAME);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.game.mouseExited(e, PanelType.GAME);
    }

    /////////////////
    // Key events override methods
    ////////////////

    @Override
    public void keyPressed(KeyEvent e) {
        this.game.keyPressed(e, PanelType.GAME);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.game.keyReleased(e, PanelType.GAME);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        this.game.keyTyped(e, PanelType.GAME);
    }

    /////////////////
    // Widget contructors
    ////////////////

    private void setGameWidgets() {

        int[] scorePos = {20, 20};
        int[] timePos = {20, 80};
        int[] topscorePos = {20, 140};

        this.timerWidget = new TimerWidget(timePos);
        this.scoreWidget = new ScoreWidget(scorePos);
        this.topscoreWidget = new TopscoreWidget(topscorePos);
        this.target = new TargetWidget();

        this.gameElements = new ArrayList<Renderable>();
        this.gameElements.add(this.timerWidget);
        this.gameElements.add(this.scoreWidget);
        this.gameElements.add(this.topscoreWidget);
        this.gameElements.add(this.target);
    }

    private void setPauseWidgets() {

        int[] screenSize = {this.getWidth(), this.getHeight()};

        this.pauseElements = new ArrayList<Renderable>();
        this.pauseElements.add(new PauseScreen(screenSize));
    }

    private void setGameOverWidget() {

        int[] screenSize = {this.getWidth(), this.getHeight()};

        this.gameOverElements = new ArrayList<Renderable>();
        this.gameOverElements.add(new GameOverScreen(screenSize));
    }

    /////////////////
    // Public methods
    ////////////////

    /**
     * Forward method call to the {@code TargetWidged} method.
     * 
     * @param e
     * @return
     */
    public boolean isTargetClicked(MouseEvent e) {
        return this.target.wasClicked(e);
    }

    /////////////////
    // Accessor methods
    ////////////////

    /**
     * Accesor method of the {@code JFrame} owner of this {@code JPanel}.
     * 
     * @return - {JFrame} object of the owner.
     */
    public JFrame getOwner() {
        return this.owner;
    }

    /**
     * Accesor for the current {@code GamePanel} screen mode.
     * 
     * @return - {@code ScreenMode} enum value.
     */
    public ScreenMode getScreenMode() {
        return this.mode;
    }

    /////////////////
    // Modifier methods
    ////////////////

    /**
     * Sets the new {@code JFrame} owner of this {@code JPanel}.
     * 
     * @param newOwner - new {@code JFrame} reference of the new owner of this
     *                 {@code JPanel}.
     */
    public void setOwner(JFrame newOwner) {
        this.owner = newOwner;
    }

    /**
     * Set's the time remaining variable, and sets the widget.
     * 
     * @param time - new time value.
     */
    public void setTimeRemaining(int time) {
        this.timerWidget.setTime(time);
    }

    /**
     * Sets a score value to the local variable, and displays it on the widget.
     * 
     * @param score - new score value.
     */
    public void setScore(int score) {
        this.scoreWidget.setScore(score);
    }

    /**
     * Sets the screen mode.
     * 
     * @param mode - new {@code ScreenMode}.
     */
    public void setScreenMode(ScreenMode mode) {
        this.mode = mode;
    }

    /**
     * Sets the topscore value for the widget.
     * 
     * @param value - new value.
     */
    public void setTopscoreWidget(int value) {
        this.topscoreWidget.setTopscore(value);
    }

    /**
     * Sets the position of the target widget.
     * 
     * @param pos - new position value.
     */
    public void setTargetWidget(int[] pos) {
        this.target.setLocation(pos);
    }

    /**
     * Sets the position and radius of the target widget.
     * 
     * @param pos - new position value.
     * @param radius - new radius value.
     */
    public void setTargetWidget(int[] pos, int radius) {
        this.target.setLocation(pos);
        this.target.setRadius(radius);
    }

    /**
     * Sets the radius of the target widget.
     * 
     * @param radius - new radius value.
     */
    public void setTargetWidget(int radius) {
        this.target.setRadius(radius);
    }

}
