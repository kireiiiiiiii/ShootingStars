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

package com.example.Interface;

import java.awt.event.MouseEvent;

/**
 * Button interface.
 * 
 */
public interface Interactable {

    /**
     * Returns the {@code MenuScreenMode} that the button will trigger when clicked.
     * 
     * @return {@code MenuScreenMode} enum value.
     */
    public MenuScreenMode getInteract();

    /**
     * Checks, if the button was clicked with the target mouse event.
     * 
     * @param e - target {@code MouseEvent}.
     * @return {@code true} if interacted, and {@code false} if not.
     */
    public boolean wasInteracted(MouseEvent e);

}
