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

package com.example.Interface.Elements.GamePanelElements;

import java.awt.*;
import com.example.Constants.Colors;
import com.example.Constants.Fonts;
import com.example.Constants.GameDialogue;
import com.example.Constants.ZOrders;
import com.example.Interface.Renderable;

/**
 * Widget, that displays score at the top of the screen.
 * 
 */
public class ScoreWidget implements Renderable {

    /////////////////
    // Constants
    ////////////////

    private final Color BACKROUND_COLOR = Colors.MAIN_GREEN;
    private final Color TEXT_COLOR = Colors.WIDGET_TEXT;

    /////////////////
    // Fields
    ////////////////

    private int currScore;
    private int[] position;

    /////////////////
    // Contructor
    ////////////////

    public ScoreWidget(int[] position) {
        this.currScore = 0;
        this.position = position;
    }

    /////////////////
    // Render methods
    ////////////////

    @Override
    public void refresh(Graphics2D g) {
        Font font = Fonts.text();

        g.setColor(BACKROUND_COLOR);
        g.fillRoundRect(this.position[0], this.position[1], 250, 50, 20, 20);
        g.setColor(TEXT_COLOR);
        g.setFont(font.deriveFont(Font.BOLD, 24));
        g.drawString(GameDialogue.score() + ": " + this.currScore, this.position[0] + 15, this.position[1] + 24 + 8);
    }

    @Override
    public int getZOrder() {
        return ZOrders.GAME_WIDGETS;
    }

    /////////////////
    // Modifiers
    ////////////////

    /**
     * Modifier method for the score.
     * 
     * @param score - new score value.
     */
    public void setScore(int score) {
        this.currScore = score;
    }

}
