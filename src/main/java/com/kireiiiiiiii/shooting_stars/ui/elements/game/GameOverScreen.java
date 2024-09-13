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

package com.kireiiiiiiii.shooting_stars.ui.elements.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Container;
import java.util.ArrayList;

import com.kireiiiiiiii.shooting_stars.constants.Colors;
import com.kireiiiiiiii.shooting_stars.constants.Fonts;
import com.kireiiiiiiii.shooting_stars.constants.GameDialogue;
import com.kireiiiiiiii.shooting_stars.constants.WidgetTags;
import com.kireiiiiiiii.shooting_stars.constants.ZIndexes;
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
    private boolean visible = false;

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
    public void render(Graphics2D g, Container img) {

        if (!visible) {
            return;
        }

        FontMetrics fm;
        int[] originArr;
        int x;
        int y;
        int sideTextOffset;
        Font headingFont = Fonts.heading();
        String mainMessage = GameDialogue.gameOver;
        String subMessage = GameDialogue.gameOverSubtext;

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
    public int getZIndex() {
        return ZIndexes.SCREENS;
    }

    @Override
    public boolean isVisible() {
        return this.visible;
    }

    @Override
    public void hide() {
        this.visible = false;
    }

    @Override
    public void show() {
        this.visible = true;
    }

    @Override
    public ArrayList<String> getTags() {
        ArrayList<String> tags = new ArrayList<String>();
        tags.add(WidgetTags.GAME_OVER);
        return tags;
    }

}
