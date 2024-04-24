/**
 * Author: Matěj Šťastný
 * Date created: 4/23/2024
 * Github link: https://github.com/kireiiiiiiii/TargetGame
 * 
 * TODO: Class header
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

class CircleClickerPanel extends JPanel {
    private Position circlePosition;
    private final int CIRCLE_RADIUS = 50;
    private final Color CIRCLE_COLOR = Color.MAGENTA;

    /**
     * Constructor for this JPanel, adds a new MouseListener and begins the game
     * 
     * @param dimension - {@code Position} object of the dimensions of the
     *                  {@code JPanel}
     */
    public CircleClickerPanel(Position dimension) {
        setPreferredSize(dimension.toDimension());

        // TODO set the init circlePosition
        circlePosition = new Position(100, 100);

        // Mouse listener to handle clicks
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (isCirleClicked(e)) {
                    // TODO generate new circle position
                    circlePosition.add(50);
                    repaint();
                }
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
