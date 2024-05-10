/**
 * Author: Matěj Šťastný
 * Date created: 4/23/2024
 * Github link: https://github.com/kireiiiiiiii/TargetGame
 * 
 * JPanel class meant to be constructed in a JFrame for the TargetGame to work
 */

package GUI;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

import Helpers.*;

class TargetGamePanelOld extends JPanel {

    // TIMER CONSTANTS
    private final int DEFUALT_DELAY = 10;
    private final Color TIMER_BACKROUND = Color.RED;
    private final Color TIMER_TEXT = Color.WHITE;
    // JPANEL CONSTATNS
    private final int WINDOW_PADDING = 10;
    // TARGET APPEARANCE CONSTANTS
    private final int CIRCLE_RADIUS = 20;
    private final Color CIRCLE_COLOR = Color.MAGENTA;
    // LEADERBOARD CONSTANTS
    private final Color LEADERBOARD_BACKROUND = Color.BLUE;
    private final Color LEADERBOARD_TEXT = Color.WHITE;
    // GAME OVER SCREEN CONSTANTS
    private final Color GAME_OVER_MAINTEXT = Color.BLACK;
    private final Color GAME_OVER_BOTTOMTEXT = Color.RED;
    // KEYBIND CONSTANTS
    private final int PAUSE_KEY = KeyEvent.VK_ESCAPE;
    private final int RESTART_KEY = KeyEvent.VK_R;
    private final int DEBUGG_KEY = KeyEvent.VK_X;
    // CLASS VARIABLES
    private Position circlePosition;
    private int score;
    private WindowMode mode;
    private PausableTimer timer;
    private int timeRemaining;

    /**
     * Enum class to handle window type to be painted on the JPanel
     */
    private enum WindowMode {
        GAME,
        PAUSE,
        GAME_OVER
    }

    /**
     * Constructor for this JPanel, adds a new MouseListener and begins the game
     * 
     * @param dimension - {@code Position} object of the dimensions of the
     *                  {@code JPanel}
     */
    public TargetGamePanelOld(Position dimension) {
        setPreferredSize(dimension.toDimension());

        // Allow keyboard input in the window
        setFocusable(true);
        requestFocusInWindow();

        // Generates a random init circle position
        this.circlePosition = new Position();
        this.circlePosition.randomize(dimension.x - CIRCLE_RADIUS, dimension.y - CIRCLE_RADIUS);

        // Set deafult variable values
        this.score = 0;
        this.mode = WindowMode.GAME;
        initializeTimer();

        // Mouse listener to handle mouse input
        addMouseListener(new MouseAdapter() {
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
        });

        // KeyListener to handle keyboard input
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // PAUSE KEY
                if (e.getKeyCode() == PAUSE_KEY) {
                    if (mode == WindowMode.GAME) {
                        mode = WindowMode.PAUSE;
                        repaint();
                    } else if (mode == WindowMode.PAUSE) {
                        mode = WindowMode.GAME;
                        repaint();
                    }
                }

                // RESTART KEY
                else if (e.getKeyCode() == RESTART_KEY) {
                    if (mode == WindowMode.GAME || mode == WindowMode.GAME_OVER) {
                        restart();
                        repaint();
                    }
                }

                // ! DEBUG KEY
                else if (e.getKeyCode() == DEBUGG_KEY) {
                    if (mode != WindowMode.GAME_OVER) {
                        mode = WindowMode.GAME_OVER;
                    } else {
                        mode = WindowMode.GAME;
                    }
                    repaint();
                }
            }
        });
    }

    /* GAME CONTROL METHODS */

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
        Runnable onFinished = () -> {onTimerFinished();};
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

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // GAME SCREEN
        if (this.mode == WindowMode.GAME) {
            this.timer.resume();
            g2d.setColor(CIRCLE_COLOR);
            g2d.fillOval(circlePosition.getIntX() - CIRCLE_RADIUS, circlePosition.getIntY() - CIRCLE_RADIUS,
                    CIRCLE_RADIUS * 2, CIRCLE_RADIUS * 2);

            paintLeaderBoard(g2d, new Position(20, 20), this.score);
            paintTimer(g2d, new Position(20, 20), this.timeRemaining);
        }

        // PAUSE SCREEN
        else if (this.mode == WindowMode.PAUSE) {
            this.timer.pause();
            paintPause(g2d);
        }

        // GAME OVER SCREEN
        else if (this.mode == WindowMode.GAME_OVER) {
            paintGameOver(g2d);
        }
    }

    /**
     * Paints the score widget on the {@code JPanel}.
     * 
     * @param g        - {@code Graphics2D} of the {@code JPanel}.
     * @param position - {@code Position} of the widget.
     * @param score    - Score to be displayed.
     */
    private void paintLeaderBoard(Graphics2D g, Position position, int score) {
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
    private void paintTimer(Graphics2D g, Position position, int score) {
        g.setColor(TIMER_BACKROUND);
        g.fillRect(position.getIntX(), position.getIntY() + 60, 200, 50);
        g.setColor(TIMER_TEXT);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("" + score, position.getIntX() + 5, position.getIntY() + 24 + 5 + 60);
    }

    /**
     * Paints the pause screen on the {@code JPanel}.
     * 
     * @param g - {@code Graphics2D} of the {@code JPanel}.
     */
    private void paintPause(Graphics2D g) {
        String text = "PAUSE";

        FontMetrics fm;
        Position origin;
        int x;
        int y;

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 80));
        fm = g.getFontMetrics();
        origin = getCenteredTextPosition(fm, text);
        x = origin.getIntX();
        y = origin.getIntY();
        g.drawString(text, x, y);
    }

    /**
     * Paints the Game over screen on the {@code JPanel}.
     * 
     * @param g - {@code Graphics2D} of the {@code JPanel}.
     */
    private void paintGameOver(Graphics2D g) {
        String mainMessg = "GAME OVER";
        String sideMessg = "Press 'R' to restart";

        FontMetrics fm;
        Position origin;
        int x;
        int y;
        int sideTextOffset;

        // Paints the main message
        g.setColor(GAME_OVER_MAINTEXT);
        g.setFont(new Font("Arial", Font.BOLD, 80));
        fm = g.getFontMetrics();
        origin = getCenteredTextPosition(fm, mainMessg);
        x = origin.getIntX();
        y = origin.getIntY();
        sideTextOffset = fm.getHeight();
        g.drawString(mainMessg, x, y);

        // Paints the smaller bottom message
        g.setColor(GAME_OVER_BOTTOMTEXT);
        g.setFont(new Font("Arial", Font.BOLD, 40));
        fm = g.getFontMetrics();
        origin = getCenteredTextPosition(fm, sideMessg);
        x = origin.getIntX();
        y = origin.getIntY();
        g.drawString(sideMessg, x, y + sideTextOffset);
    }

    /* FONT & TEXT HANDELING METHODS */

    /**
     * Returns the origin point of a {@code drawString()} method for a text.
     * 
     * @param fm   - {@code FontMetrics} of the font that you want the message to be
     *             painted with.
     * @param text - {@code String} of the text you want to paint.
     * @return {@code Position} object of the origin point.
     */
    private Position getCenteredTextPosition(FontMetrics fm, String text) {
        int x = (getWidth() - fm.stringWidth(text)) / 2;
        int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
        return new Position(x, y);
    }
}
