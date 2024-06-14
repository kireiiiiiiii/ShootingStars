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

package com.example.Interface.GamePanelElements;

import java.awt.*;
import com.example.Interface.Renderable;

/**
 * Widget, that displays time left
 * 
 */
public class TimerWidget implements Renderable {

    /////////////////
    // Constants
    ////////////////
    
    private final Color TIMER_BACKROUND = Color.RED;
    private final Color TIMER_TEXT = Color.WHITE;

    /////////////////
    // Variables
    ////////////////

    private int timeLeft;
    private int[] position;

    /////////////////
    // Constructors
    ////////////////

    public TimerWidget(int[] position, int time) {
        this.timeLeft = time;
        this.position = position;
    }

    /////////////////
    // Render methods
    ////////////////

    @Override
    public void refresh(Graphics2D g) {
        g.setColor(TIMER_BACKROUND);
        g.fillRect(this.position[0], this.position[1] + 60, 200, 50);
        g.setColor(TIMER_TEXT);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("" + this.timeLeft, this.position[0] + 5, this.position[1] + 24 + 5 + 60);
    }
    
    /////////////////
    // Modifiers
    ////////////////

    /**
     * Modifier method for the time.
     * 
     * @param time - new value.
     */
    public void setTime(int time) {
        this.timeLeft = time;
    }


}
