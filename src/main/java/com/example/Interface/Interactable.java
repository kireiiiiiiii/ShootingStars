/*
 * Author: Matěj Šťastný
 * Date created: 6/13/2024
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

/**
 * Interface for UI elements that can be interacted with.
 * 
 */
public interface Interactable {

    /**
     * Method called when an UI is interacted with (clicked on).
     * 
     * @param e - a {@code MouseEvent} object of the event that caused the interact.
     */
    public void interact(MouseEvent e);

}
