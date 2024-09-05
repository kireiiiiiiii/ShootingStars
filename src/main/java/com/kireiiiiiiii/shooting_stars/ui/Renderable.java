/*
 * Author: Matěj Šťastný
 * Date created: 6/13/2024
 * Github link: https://github.com/kireiiiiiiii/ShootingStars
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

package com.kireiiiiiiii.shooting_stars.ui;

import java.awt.*;

/**
 * Intefrace for GUI renderable objects.
 * 
 */
public interface Renderable {

    /**
     * Repaints the object onto the designated {@code JPanel} specified by the
     * {@code Graphics2D} object.
     * 
     * @param g2d - the {@Graphics2D} object of the {@code JPanel}.
     */
    public void refresh(Graphics2D g2d);

    /**
     * Returns the Z layer value for the renderable.
     * 
     * @return a Z layer value.
     */
    public int getZOrder();

}