/*
 * Author: Matěj Šťastný
 * Date created: 6/14/2024
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

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import com.example.Constants.Colors;
import com.example.Constants.Fonts;
import com.example.Constants.GameDialogue;
import com.example.Constants.ZOrders;
import com.example.Interface.Renderable;
import com.example.Tools.FontUtil;

/**
 * A {@code Renderable} game over screen.
 * 
 */
public class GameOverScreen implements Renderable {

    /////////////////
    // Constants
    ////////////////

    private final Color MAIN_TEXT_COLOR = Colors.MAIN_TEXT;
    private final Color SUBTEXT_COLOR = Colors.SUB_TEXT;
    private final Color SCORES_COLOR = Colors.SUB_TEXT;

    /////////////////
    // Variables
    ////////////////

    private int currScore;
    private int topscore;
    private int[] size;

    /////////////////
    // Contrsuctors
    ////////////////

    /**
     * Default constructor.
     * 
     * @param size - size of the game over screen, commonly the size of the owning
     *             {@code JPanel}.
     */
    public GameOverScreen(int[] size) {
        this.size = size;
        currScore = -1;
        topscore = -1;
    }

    /////////////////
    // Render
    ////////////////

    @Override
    public void refresh(Graphics2D g) {

        FontMetrics fm;
        int[] originArr;
        int x;
        int y;
        int sideTextOffset;
        Font headingFont = Fonts.heading();
        Font scoreFont = Fonts.text();
        String mainMessage = GameDialogue.gameOver();
        String subMessage = GameDialogue.gameOverSubtext();
        String scoreMessage = GameDialogue.score() + ": ";
        String topscoreMessage = GameDialogue.topscore() + ": ";

        // Paints the main message
        g.setColor(this.MAIN_TEXT_COLOR);
        g.setFont(headingFont.deriveFont(Font.PLAIN, 80));
        fm = g.getFontMetrics();
        originArr = FontUtil.getCenteredPos(this.size[0], this.size[1], fm, mainMessage);
        x = originArr[0];
        y = originArr[1];
        sideTextOffset = fm.getHeight();
        g.drawString(mainMessage, x, y);

        // Paints the smaller bottom message
        g.setColor(SUBTEXT_COLOR);
        g.setFont(headingFont.deriveFont(Font.PLAIN, 40));
        fm = g.getFontMetrics();
        originArr = FontUtil.getCenteredPos(this.size[0], this.size[1], fm, subMessage);
        x = originArr[0];
        y = originArr[1];
        g.drawString(subMessage, x, y + sideTextOffset);

        g.setColor(SUBTEXT_COLOR);
        g.setFont(scoreFont.deriveFont(Font.PLAIN, 40));
        fm = g.getFontMetrics();
        int[] leftUpCorner = { 0, 0 };
        originArr = FontUtil.getSouthEastPos(fm,  topscoreMessage + this.topscore, leftUpCorner);
        x = originArr[0] + 20;
        y = originArr[1];
        g.drawString(topscoreMessage + this.topscore, x, y);

        g.setColor(SCORES_COLOR);
        g.setFont(scoreFont.deriveFont(Font.PLAIN, 40));
        fm = g.getFontMetrics();
        int[] rightUpCorner = { size[0], 0 };
        originArr = FontUtil.getSouthWestPos(fm, scoreMessage + this.currScore, rightUpCorner);
        x = originArr[0] - 20;
        y = originArr[1];
        g.drawString(scoreMessage + this.currScore, x, y);

    }

    @Override
    public int getZOrder() {
        return ZOrders.SCREENS;
    }

    /////////////////
    // Modifiers
    ////////////////

    /**
     * Current score setter.
     * 
     * @param score - new value.
     */
    public void setScore(int score) {
        this.currScore = score;
    }

    /**
     * Top score setter.
     * 
     * @param topscore - new value
     */
    public void setTopscore(int topscore) {
        this.topscore = topscore;
    }

}
