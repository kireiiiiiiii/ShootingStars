/**
 * Author: Matěj Šťastný
 * Date created: 4/23/2024
 * Github link: https://github.com/kireiiiiiiii/TargetGame
 * 
 * TODO: Class header
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

class CircleClickerPanel extends JPanel {
    private final int CIRCLE_RADIUS = 20;
    private final Color CIRCLE_COLOR = Color.MAGENTA;
    private final Color LEADERBOARD_BACKROUND = Color.BLUE;
    private final Color LEADERBOARD_TEXT = Color.WHITE;
    
    private Position circlePosition;
    private int score;
    private WindowMode mode;

    /**
     * Enum class to handle window type to be painted on the JPanel
     */
    private enum WindowMode {
        GAME,
        PAUSE
    }

    /**
     * Constructor for this JPanel, adds a new MouseListener and begins the game
     * 
     * @param dimension - {@code Position} object of the dimensions of the
     *                  {@code JPanel}
     */
    public CircleClickerPanel(Position dimension) {
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

        // Mouse listener to handle mouse input
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (mode == WindowMode.GAME) {
                    if (isCirleClicked(e)) {
                        // Generates a new random circle position and refreshes the panel
                        circlePosition.randomize(dimension.x - CIRCLE_RADIUS, dimension.y - CIRCLE_RADIUS);
                        score += 10;
                    }
                    else {
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
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    if (mode == WindowMode.GAME) {
                        mode = WindowMode.PAUSE;
                        repaint();
                    }
                    else if (mode == WindowMode.PAUSE) {
                        mode = WindowMode.GAME;
                        repaint();
                    }
                }
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // GAME MODE
        if (this.mode == WindowMode.GAME) {
            g2d.setColor(CIRCLE_COLOR);
            g2d.fillOval(circlePosition.getIntX() - CIRCLE_RADIUS, circlePosition.getIntY() - CIRCLE_RADIUS,
                    CIRCLE_RADIUS * 2, CIRCLE_RADIUS * 2);

            paintLeaderBoard(g2d, new Position(20, 20), score);
        }

        // PAUSE MODE
        else if (this.mode == WindowMode.PAUSE) {
            paintPause(g2d);
        }
    }

    /**
     * Paints the score widget on the {@code JPanel}. 
     * 
     * @param g - {@code Graphics2D} of the {@code JPanel}. 
     * @param position - {@code Position} of the widget. 
     * @param score - Score to be displayed. 
     */
    private void paintLeaderBoard(Graphics2D g, Position position, int score) {
        g.setColor(LEADERBOARD_BACKROUND);
        g.fillRect(position.getIntX(), position.getIntY(), 200, 50);
        g.setColor(LEADERBOARD_TEXT);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("" + score, position.getIntX() + 5, position.getIntY() + 24 + 5);
    }

    /**
     * Paints the pause screen on the {@code JPanel}. 
     * 
     * @param g - {@code Graphics2D} of the {@code JPanel}. 
     */
    private void paintPause(Graphics2D g) {
        String text = "PAUSE";
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 80));
        FontMetrics fm = g.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(text)) / 2;
        int y = (getHeight() - fm.getHeight()) / 2 + fm.getAscent();
        g.drawString(text, x, y);
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
}
