/*
 * Author: Matěj Šťastný
 * Date created: 6/14/2024
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

package com.example.Interface.GamePanelElements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import com.example.Interface.Renderable;

/**
 * The target widget.
 * 
 */
public class TargetWidget implements Renderable {

    /////////////////
    // Constants
    ////////////////

    private final Color CIRCLE_COLOR = Color.MAGENTA;

    /////////////////
    // Variables
    ////////////////

    private int radius;
    private int[] location;

    /////////////////
    // Constructor
    ////////////////

    /**
     * Default contstructor.
     * 
     */
    public TargetWidget() {
        this.radius = 10;
        this.location = new int[2];
        this.location[0] = 0;
        this.location[1] = 0;
    }

    /////////////////
    // Render
    ////////////////

    @Override
    public void refresh(Graphics2D g) {
        g.setColor(CIRCLE_COLOR);
        g.fillOval(this.location[0] - this.radius, this.location[1] - this.radius,
                this.radius * 2, this.radius * 2);
    }

    /////////////////
    // Public methods
    ////////////////

    /**
     * Calculates, if the target got clicked depending on its location, and the
     * location of the click.
     * 
     * @param e - {@code MouseEvent} of the interaction.
     * @return - {@code boolean} if the target was hit.
     */
    public boolean wasClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        return Math.abs(this.location[0] - x) <= this.radius
                && Math.abs(this.location[1] - y) <= this.radius;
    }

    /////////////////
    // Modifiers
    ////////////////

    /**
     * Target location setter.
     * 
     * @param location - new location value.
     */
    public void setLocation(int[] location) {
        this.location = location;
    }

    /**
     * Radius setter.
     * 
     * @param radius - new radius value.
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

}
