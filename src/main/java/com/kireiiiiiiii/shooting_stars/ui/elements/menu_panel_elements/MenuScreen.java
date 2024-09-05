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

package com.kireiiiiiiii.shooting_stars.ui.elements.menu_panel_elements;

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
 * Main menu screen, shown to player with the game launch.
 * 
 */
public class MenuScreen implements Renderable {

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
    // Constructor
    ////////////////

    /**
     * Default constructor.
     * 
     * @param size - size of the game over screen, commonly the size of the owning
     *             {@code JPanel}.
     */
    public MenuScreen(int[] size) {
        this.size = size;
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
        int sideTextOffset;
        String mainMessage = GameDialogue.appTitle();
        String subMessage = GameDialogue.menuSubText();

        // Paints the main message
        g.setColor(this.MAIN_TEXT_COLOR);
        g.setFont(Fonts.heading().deriveFont(Font.PLAIN, 80));
        fm = g.getFontMetrics();
        originArr = FontUtil.getCenteredPos(this.size[0], this.size[1], fm, mainMessage);
        origin = new Vec2D(originArr[0], originArr[1]);
        x = origin.getIntX();
        y = origin.getIntY();
        sideTextOffset = fm.getHeight();
        g.drawString(mainMessage, x, y);

        // Paints the smaller bottom message
        g.setColor(SUBTEXT_COLOR);
        g.setFont(Fonts.heading().deriveFont(Font.PLAIN, 40));
        fm = g.getFontMetrics();
        originArr = FontUtil.getCenteredPos(this.size[0], this.size[1], fm, subMessage);
        origin = new Vec2D(originArr[0], originArr[1]);
        x = origin.getIntX();
        y = origin.getIntY();
        g.drawString(subMessage, x, y + sideTextOffset);
    }

    @Override
    public int getZOrder() {
        return ZOrders.SCREENS;
    }

}
