/*
 * Author: Matěj Šťastný
 * Date created: 6/14/2024
 * Github link: https://github.com/kireiiiiiiii/TargetGame
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

import com.example.Constants.ZOrders;
import com.example.Interface.Renderable;

/**
 * Widget to display players top score.
 * 
 */
public class TopscoreWidget implements Renderable {
    
    /////////////////
    // Constants
    ////////////////
    
    private final Color TIMER_BACKROUND = Color.GREEN;
    private final Color TIMER_TEXT = Color.WHITE;

    /////////////////
    // Variables
    ////////////////

    private int topscore;
    private int[] position;

    /////////////////
    // Constructors
    ////////////////

    /**
     * Deafult contructor with a default widget topscore value.
     * 
     * @param position - position of the widget.
     * @param topscore - default topscore value.
     */
    public TopscoreWidget(int[] position, int topscore) {
        this.topscore = topscore;
        this.position = position;
    }

    /**
     * Constructor without a default topscore value given, is set to 0.
     * 
     * @param position - position of the widget.
     */
    public TopscoreWidget(int[] position) {
        this(position, 0);
    }

    /////////////////
    // Render methods
    ////////////////

    @Override
    public void refresh(Graphics2D g) {
        g.setColor(TIMER_BACKROUND);
        g.fillRect(this.position[0], this.position[1], 200, 50);
        g.setColor(TIMER_TEXT);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("" + this.topscore, this.position[0] + 5, this.position[1] + 24 + 5);
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
     * @param topscore - new value.
     */
    public void setTopscore(int topscore) {
        this.topscore = topscore;
    }


}
