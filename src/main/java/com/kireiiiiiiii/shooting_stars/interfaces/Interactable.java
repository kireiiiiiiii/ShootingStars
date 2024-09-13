/*
 * Author: Matěj Šťastný
 * Date created: 6/16/2024
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

package com.kireiiiiiiii.shooting_stars.interfaces;

import java.awt.event.MouseEvent;

/**
 * Interface for interactable ui elements.
 * 
 */
public interface Interactable {

    /**
     * Checks, if the button was clicked with the target mouse event.
     * 
     * @param e - target {@code MouseEvent}.
     * @return {@code true} if interacted, and {@code false} if not.
     */
    public boolean wasInteracted(MouseEvent e);

    /**
     * Accesor method for the event, that is supposed to happen on interact.
     * 
     * @return a {@code Runnable} event.
     */
    public Runnable getInteraction();

}
