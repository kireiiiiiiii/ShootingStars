/*
 * Author: Matěj Šťastný
 * Date created: 7/27/2024
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
 *
 */

package com.kireiiiiiiii.shooting_stars.ui;

import java.awt.Graphics2D;
import java.awt.Container;
import java.util.ArrayList;

/**
 * Interface for objects that can be rendered onto a {@code JPanel} object.
 * 
 */
public interface Renderable {

    /**
     * This method renders the object using a {@code Graphics2D} object reference.
     * 
     * @param g - {@code Graphocs2D} object reference of the target {@code JPanel}
     *          object.
     */
    public void render(Graphics2D g, Container focusCycleRootAncestor);

    /**
     * This method returns the Z-Index of the object. The Z-Index cannot be changed!
     * 
     * @return {@code int} value of the Z-Index.
     */
    public int getZIndex();

    /**
     * This method returns if the item is hidden or shown, therefore should be
     * rendered or not.
     * 
     * @return {@code boolean} visible/hidden.
     */
    public boolean isVisible();

    public void hide();

    public void show();

    public ArrayList<String> getTags();

}