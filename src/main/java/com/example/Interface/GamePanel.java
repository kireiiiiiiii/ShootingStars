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
import javax.swing.*;
import com.example.Common.*;
import com.example.Constants.*;
import com.example.Tools.*;

import java.awt.*;

/**
 * TODO: Make class header
 * 
 */
public class GamePanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

    /////////////////
    // Constants
    ////////////////

    // Timer
    private final int DEFUALT_DELAY = 20;
    private final Color TIMER_BACKROUND = Color.RED;
    private final Color TIMER_TEXT = Color.WHITE;
    // Padding
    private final int WINDOW_PADDING = 10;
    // Target
    private final int CIRCLE_RADIUS = 20;
    private final Color CIRCLE_COLOR = Color.MAGENTA;
    // Leaderboard
    private final Color LEADERBOARD_BACKROUND = Color.BLUE;
    private final Color LEADERBOARD_TEXT = Color.WHITE;
    // Game over screen
    private final Color GAME_OVER_MAINTEXT = Color.BLACK;
    private final Color GAME_OVER_BOTTOMTEXT = Color.RED;

    /////////////////
    // Class variables
    ////////////////

    private Vec2D circlePosition;
    private int score;
    private WindowMode mode;
    private PausableTimer timer;
    private int timeRemaining;
    private JFrame owner;

    /////////////////
    // Enum classes
    ////////////////

    /**
     * Enum class to handle window type to be painted on the JPanel
     */
    private enum WindowMode {
        GAME,
        PAUSE,
        GAME_OVER
    }

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

        // Generates a random init circle position
        this.circlePosition = new Vec2D();
        this.circlePosition.randomize(getWidth() - CIRCLE_RADIUS, getHeight() - CIRCLE_RADIUS);

        // Set deafult variable values
        this.score = 0;
        this.mode = WindowMode.GAME;
        initializeTimer();
    }

    /////////////////
    // JPanel override methods
    ////////////////

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;

        if (mode == WindowMode.GAME) {
            paintGame(g);
        } else if (mode == WindowMode.PAUSE) {
            paintPause(g);
            ;
        } else if (mode == WindowMode.GAME_OVER) {
            paintGameOver(g);
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
    // Paint methods
    ////////////////

    /**
     * Default paint method for the game environtment.
     * 
     * @param g - a {@code Graphic2D} object of the target paint panel.
     */
    private void paintGame(Graphics2D g) {
        // Just in case a previous mode paused the timer
        this.timer.resume();

        // Paint the target
        g.setColor(CIRCLE_COLOR);
        g.fillOval(circlePosition.getIntX() - CIRCLE_RADIUS, circlePosition.getIntY() - CIRCLE_RADIUS,
                CIRCLE_RADIUS * 2, CIRCLE_RADIUS * 2);

        // Paint the widgets
        paintLeaderBoard(g, new Vec2D(20, 20), this.score);
        paintTimer(g, new Vec2D(20, 20), this.timeRemaining);
    }

    /**
     * Paint method for the pause mode.
     * 
     * @param g- a {@code Graphic2D} object of the target paint panel.
     */
    private void paintPause(Graphics2D g) {
        // Stop the timer when swiched into
        this.timer.pause();

        String text = "PAUSE";
        FontMetrics fm;
        Vec2D origin;
        int[] originArr;
        int x;
        int y;

        g.setColor(Color.BLACK);
        g.setFont(Fonts.HEADING.deriveFont(Font.BOLD, 80));
        fm = g.getFontMetrics();
        originArr = FontUtil.getCenteredPos(this.getWidth(), this.getHeight(), fm, text);
        origin = new Vec2D(originArr[0], originArr[1]);
        x = origin.getIntX();
        y = origin.getIntY();
        g.drawString(text, x, y);
    }

    /**
     * Paint the 'Game Over' screen.
     * 
     * @param g - a {@code Graphic2D} object of the target paint panel.
     */
    private void paintGameOver(Graphics2D g) {
        String mainMessg = "GAME OVER";
        String sideMessg = "Press R to restart";
        FontMetrics fm;
        Vec2D origin;
        int[] originArr;
        int x;
        int y;
        int sideTextOffset;

        // Paints the main message
        g.setColor(this.GAME_OVER_MAINTEXT);
        g.setFont(Fonts.HEADING.deriveFont(Font.BOLD, 80));
        fm = g.getFontMetrics();
        originArr = FontUtil.getCenteredPos(this.getWidth(), this.getHeight(), fm, mainMessg);
        origin = new Vec2D(originArr[0], originArr[1]);
        x = origin.getIntX();
        y = origin.getIntY();
        sideTextOffset = fm.getHeight();
        g.drawString(mainMessg, x, y);

        // Paints the smaller bottom message
        g.setColor(GAME_OVER_BOTTOMTEXT);
        g.setFont(Fonts.HEADING.deriveFont(Font.PLAIN, 40));
        fm = g.getFontMetrics();
        originArr = FontUtil.getCenteredPos(this.getWidth(), this.getHeight(), fm, sideMessg);
        origin = new Vec2D(originArr[0], originArr[1]);
        x = origin.getIntX();
        y = origin.getIntY();
        g.drawString(sideMessg, x, y + sideTextOffset);
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
        // MOUSE IS RELEASED IN GAME
        if (mode == WindowMode.GAME) {
            if (isCirleClicked(e)) {
                /**
                 * Generates a new random circle position and refreshes the panel
                 * X - Width of the panel minus the size of the circle & the padding (the times
                 * two is because it's later added to pad the left side too)
                 * Y - Height of the panel minus the size of the circle & the padding (the times
                 * two is because it's later added to pad the upper side too)
                 */
                circlePosition.randomize(getWidth() - CIRCLE_RADIUS * 2 - WINDOW_PADDING * 2,
                        getHeight() - CIRCLE_RADIUS * 2 - WINDOW_PADDING + 2);
                circlePosition.x += WINDOW_PADDING;
                circlePosition.y += WINDOW_PADDING;

                // Incremets the score
                score += 10;
            } else {
                // Subtracts the score
                score -= 10;
            }
            repaint();
        }
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
        // PAUSE KEY
        if (e.getKeyCode() == Keybinds.PAUSE_KEY) {
            if (mode == WindowMode.GAME) {
                mode = WindowMode.PAUSE;
                repaint();
            } else if (mode == WindowMode.PAUSE) {
                mode = WindowMode.GAME;
                repaint();
            }
        }

        // RESTART KEY
        else if (e.getKeyCode() == Keybinds.RESTART_KEY) {
            if (mode == WindowMode.GAME || mode == WindowMode.GAME_OVER) {
                restart();
                repaint();
            }
        }

        // ! DEBUG KEY
        else if (e.getKeyCode() == Keybinds.DEBUGG_KEY) {
            if (mode != WindowMode.GAME_OVER) {
                mode = WindowMode.GAME_OVER;
            } else {
                mode = WindowMode.GAME;
            }
            repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

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

    /////////////////
    // Other public methods
    ////////////////

    /////////////////
    // Private methods
    ////////////////

    /**
     * Restarts the game
     */
    private void restart() {
        this.mode = WindowMode.GAME;
        repaint();
        this.score = 0;
        this.circlePosition.randomize(getWidth(), getHeight());
        initializeTimer();
    }

    /**
     * Checks if the {@code MouseEvent} was a click on the circle
     * 
     * @param e - {@code MouseEvent} of the click
     * @return was clicked?
     */
    private boolean isCirleClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        return Math.abs(this.circlePosition.getIntX() - x) <= CIRCLE_RADIUS
                && Math.abs(this.circlePosition.getIntY() - y) <= CIRCLE_RADIUS;
    }

    /* TIMER METHODS */

    private void initializeTimer() {
        if (this.timer != null) {
            timer.forceStop();
        }
        Runnable onFinished = () -> {
            onTimerFinished();
        };
        Runnable everyRun = () -> {
            this.timeRemaining = (int) this.timer.getTimeRemaining() / 1000;
            repaint();
        };
        this.timer = new PausableTimer(1000, DEFUALT_DELAY + 1, onFinished, everyRun);
        timer.start();
    }

    private void onTimerFinished() {
        System.out.println("TIMER DONE");
        this.mode = WindowMode.GAME_OVER;
        repaint();
    }

    /* PAINT METHODS */

    /**
     * Paints the score widget on the {@code JPanel}.
     * 
     * @param g        - {@code Graphics2D} of the {@code JPanel}.
     * @param position - {@code Position} of the widget.
     * @param score    - Score to be displayed.
     */
    private void paintLeaderBoard(Graphics2D g, Vec2D position, int score) {
        g.setColor(LEADERBOARD_BACKROUND);
        g.fillRect(position.getIntX(), position.getIntY(), 200, 50);
        g.setColor(LEADERBOARD_TEXT);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("" + score, position.getIntX() + 5, position.getIntY() + 24 + 5);
    }

    /**
     * Paints the score widget on the {@code JPanel}.
     * 
     * @param g        - {@code Graphics2D} of the {@code JPanel}.
     * @param position - {@code Position} of the widget.
     * @param score    - Score to be displayed.
     */
    private void paintTimer(Graphics2D g, Vec2D position, int score) {
        g.setColor(TIMER_BACKROUND);
        g.fillRect(position.getIntX(), position.getIntY() + 60, 200, 50);
        g.setColor(TIMER_TEXT);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("" + score, position.getIntX() + 5, position.getIntY() + 24 + 5 + 60);
    }
}
