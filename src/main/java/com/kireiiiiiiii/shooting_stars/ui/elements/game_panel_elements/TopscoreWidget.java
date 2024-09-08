/*
 * Author: Matěj Šťastný
 * Date created: 6/14/2024
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

package com.kireiiiiiiii.shooting_stars.ui.elements.game_panel_elements;

import java.awt.*;

import com.kireiiiiiiii.shooting_stars.constants.Colors;
import com.kireiiiiiiii.shooting_stars.constants.Fonts;
import com.kireiiiiiiii.shooting_stars.constants.GameDialogue;
import com.kireiiiiiiii.shooting_stars.constants.ZOrders;
import com.kireiiiiiiii.shooting_stars.ui.Renderable;

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
        Font font = Fonts.text();
        g.setColor(TIMER_BACKROUND);
        g.fillRoundRect(this.position[0], this.position[1], 250, 50, 20, 20);
        g.setColor(TIMER_TEXT);
        g.setFont(font.deriveFont(Font.BOLD, 24));
        g.drawString(GameDialogue.topscore + ": " + this.topscore, this.position[0] + 15, this.position[1] + 24 + 8);
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
