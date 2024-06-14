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
import com.example.Interface.GamePanelElements.ScoreWidget;
import com.example.Interface.GamePanelElements.TimerWidget;

import java.awt.*;

/**
 * TODO: Make class header
 * 
 */
public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

    /////////////////
    // Constants
    ////////////////

    private final int WINDOW_PADDING = 10;

    /////////////////
    // Class variables
    ////////////////

    private ScreenMode mode;
    private JFrame owner;

    /////////////////
    // Renderables
    ////////////////

    private ArrayList<Renderable> gameElements;
    private ArrayList<Renderable> pauseElements;
    private ArrayList<Renderable> gameOverElements;
    private ScoreWidget scoreWidget;
    private TimerWidget timerWidget;

    /////////////////
    // Constructors
    ////////////////

    /**
     * Standart constructor
     * 
     * @param owner - {JFrame} object, that owns this {@code JPanel}.
     */
    public GamePanel(JFrame owner) {
        this.owner = owner;
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

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /////////////////
    // Key events override methods
    ////////////////

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /////////////////
    // Widget contructors
    ////////////////

    private void setGameWidgets() {

        int[] pos = {20, 20};

        this.timerWidget = new TimerWidget(pos, 99);
        this.scoreWidget = new ScoreWidget(pos);

        this.gameElements = new ArrayList<Renderable>();
        this.gameElements.add(this.timerWidget);
        this.gameElements.add(this.scoreWidget);
    }

    private void setPauseWidgets() {

    }

    private void setGameOverWidget() {

    }

    /////////////////
    // Events
    ////////////////

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

}
