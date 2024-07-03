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

package com.example.Interface.Elements.GamePanelElements;

import java.awt.*;

import com.example.Constants.Colors;
import com.example.Constants.Fonts;
import com.example.Constants.GameDialogue;
import com.example.Constants.ZOrders;
import com.example.Interface.Renderable;

/**
 * Widget, that displays time left
 * 
 */
public class TimerWidget implements Renderable {

    /////////////////
    // Constants
    ////////////////
    
    private final Color TIMER_BACKROUND = Colors.SCORES_WIDGETS;
    private final Color TIMER_TEXT = Colors.WIDGET_TEXT;

    /////////////////
    // Variables
    ////////////////

    private int timeLeft;
    private int[] position;

    /////////////////
    // Constructors
    ////////////////

    /**
     * Default contructor.
     * 
     * @param position - position of the widget.
     * @param time - display time.
     */
    public TimerWidget(int[] position, int time) {
        this.timeLeft = time;
        this.position = position;
    }

    /**
     * Constructor without an initial time set.
     * 
     * @param position - position of the widget.
     */
    public TimerWidget(int[] position) {
        this(position, 0);
    }

    /////////////////
    // Render methods
    ////////////////

    @Override
    public void refresh(Graphics2D g) {
        Font font = Fonts.text();
        g.setColor(TIMER_BACKROUND);
        g.fillRoundRect(this.position[0], this.position[1], 250, 50, 20, 20);
        g.setColor(TIMER_TEXT);
        g.setFont(font.deriveFont(Font.BOLD, 24));
        g.drawString(GameDialogue.timeLeft() + ": " + this.timeLeft, this.position[0] + 15, this.position[1] + 24 + 8);
    }

    @Override
    public int getZOrder() {
        return ZOrders.GAME_WIDGETS;
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
