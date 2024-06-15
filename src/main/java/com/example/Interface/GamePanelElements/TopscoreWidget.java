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

import com.example.Constants.Colors;
import com.example.Constants.Fonts;
import com.example.Constants.GameDialogue;
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
    
    private final Color TIMER_BACKROUND = Colors.SCORES_WIDGETS;
    private final Color TIMER_TEXT = Colors.WIDGET_TEXT;
    private final Font FONT = Fonts.TEXT;

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
        g.fillRoundRect(this.position[0], this.position[1], 200, 50, 20, 20);
        g.setColor(TIMER_TEXT);
        g.setFont(FONT.deriveFont(Font.BOLD, 24));
        g.drawString(GameDialogue.topscore() + ": " + this.topscore, this.position[0] + 15, this.position[1] + 24 + 8);
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
