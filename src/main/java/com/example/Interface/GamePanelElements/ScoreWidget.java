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
 * Widget, that displays score at the top of the screen.
 * 
 */
public class ScoreWidget implements Renderable {

    /////////////////
    // Constants
    ////////////////

    private final Color BACKROUND_COLOR = Color.BLUE;
    private final Color TEXT_COLOR = Color.WHITE;

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
        g.setColor(BACKROUND_COLOR);
        g.fillRect(this.position[0], this.position[1], 200, 50);
        g.setColor(TEXT_COLOR);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("" + this.currScore, this.position[0] + 5, this.position[1] + 24 + 5);
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
