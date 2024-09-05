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

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

import com.kireiiiiiiii.shooting_stars.constants.Colors;
import com.kireiiiiiiii.shooting_stars.constants.Fonts;
import com.kireiiiiiiii.shooting_stars.constants.GameDialogue;
import com.kireiiiiiiii.shooting_stars.constants.ZOrders;
import com.kireiiiiiiii.shooting_stars.tools.FontUtil;
import com.kireiiiiiiii.shooting_stars.ui.Renderable;

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

    /////////////////
    // Variables
    ////////////////

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
        String mainMessage = GameDialogue.gameOver();
        String subMessage = GameDialogue.gameOverSubtext();

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

    }

    @Override
    public int getZOrder() {
        return ZOrders.SCREENS;
    }

}
