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

import com.kireiiiiiiii.shooting_stars.common.Vec2D;
import com.kireiiiiiiii.shooting_stars.constants.Colors;
import com.kireiiiiiiii.shooting_stars.constants.Fonts;
import com.kireiiiiiiii.shooting_stars.constants.GameDialogue;
import com.kireiiiiiiii.shooting_stars.constants.ZOrders;
import com.kireiiiiiiii.shooting_stars.tools.FontUtil;
import com.kireiiiiiiii.shooting_stars.ui.Renderable;

/**
 * Pause screen {@code Renderable}.
 * 
 */
public class PauseScreen implements Renderable {

    /////////////////
    // Constants
    ////////////////

    private final Color TEXT_COLOR = Colors.MAIN_TEXT;

    /////////////////
    // Variables
    ////////////////

    private int[] size;

    /////////////////
    // Constructor
    ////////////////

    /**
     * Default contructor.
     * 
     * @param panelSize - determines the size of the screen, commonly same as the
     *                  owning {@code JPanel}.
     */
    public PauseScreen(int[] panelSize) {
        this.size = panelSize;
    }

    /////////////////
    // Render
    ////////////////

    @Override
    public void refresh(Graphics2D g) {

        FontMetrics fm;
        Vec2D origin;
        int[] originArr;
        int x;
        int y;
        String text = GameDialogue.pause();

        g.setColor(TEXT_COLOR);
        g.setFont(Fonts.heading().deriveFont(Font.PLAIN, 80));
        fm = g.getFontMetrics();
        originArr = FontUtil.getCenteredPos(size[0], size[1], fm, text);
        origin = new Vec2D(originArr[0], originArr[1]);
        x = origin.getIntX();
        y = origin.getIntY();
        g.drawString(text, x, y);
    }

    @Override
    public int getZOrder() {
        return ZOrders.SCREENS;
    }

}
