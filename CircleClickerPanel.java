/**
 * Author: Matěj Šťastný
 * Date created: 4/23/2024
 * Github link: https://github.com/kireiiiiiiii/TargetGame
 * 
 * TODO: Class header
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

class CircleClickerPanel extends JPanel {
    private final int CIRCLE_RADIUS = 20;
    private final Color CIRCLE_COLOR = Color.MAGENTA;
    private final Color LEADERBOARD_BACKROUND = Color.BLUE;
    private final Color LEADERBOARD_TEXT = Color.WHITE;
    
    private Position circlePosition;
    private int score;

    /**
     * Constructor for this JPanel, adds a new MouseListener and begins the game
     * 
     * @param dimension - {@code Position} object of the dimensions of the
     *                  {@code JPanel}
     */
    public CircleClickerPanel(Position dimension) {
        setPreferredSize(dimension.toDimension());

        // Generates a random init circle position
        circlePosition = new Position();
        circlePosition.randomize(dimension.x - CIRCLE_RADIUS, dimension.y - CIRCLE_RADIUS);
        score = 0;

        // Mouse listener to handle clicks
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
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
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(CIRCLE_COLOR);
        g2d.fillOval(circlePosition.getIntX() - CIRCLE_RADIUS, circlePosition.getIntY() - CIRCLE_RADIUS,
                CIRCLE_RADIUS * 2, CIRCLE_RADIUS * 2);

        paintLeaderBoard(g2d, new Position(20, 20), score);
    }

    private void paintLeaderBoard(Graphics2D g, Position position, int score) {
        g.setColor(LEADERBOARD_BACKROUND);
        g.fillRect(position.getIntX(), position.getIntY(), 200, 50);
        g.setColor(LEADERBOARD_TEXT);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("" + score, position.getIntX() + 5, position.getIntY() + 24 + 5);
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
