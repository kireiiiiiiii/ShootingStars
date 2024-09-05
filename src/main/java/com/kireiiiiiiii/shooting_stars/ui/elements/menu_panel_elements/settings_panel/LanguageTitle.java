/*
 * Author: Matěj Šťastný
 * Date created: 6/18/2024
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

package com.kireiiiiiiii.shooting_stars.ui.elements.menu_panel_elements.settings_panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import com.kireiiiiiiii.shooting_stars.constants.Fonts;
import com.kireiiiiiiii.shooting_stars.constants.GameDialogue;
import com.kireiiiiiiii.shooting_stars.constants.ZOrders;
import com.kireiiiiiiii.shooting_stars.tools.FontUtil;
import com.kireiiiiiiii.shooting_stars.ui.Renderable;

/**
 * Widget to display the current game language.
 * 
 */
public class LanguageTitle implements Renderable {

    /////////////////
    // Constants
    ////////////////

    public static final int[] SIZE = { 400, 80 };
    private static final int ROUNDED = 50;
    private static final int BORDER_HEIGHT = 10;

    /////////////////
    // Variables
    ////////////////

    private int[] position;

    /////////////////
    // Constructors
    ////////////////

    /**
     * Default constructor.
     * 
     * @param pos - position of the widget.
     */
    public LanguageTitle(int[] pos) {
        this.position = pos;
    }

    /////////////////
    // Render
    ////////////////

    @Override
    public void refresh(Graphics2D g) {
        String text = GameDialogue.currentLanguage();

        g.setColor(Color.BLACK);
        g.fillRoundRect(this.position[0] - BORDER_HEIGHT / 2, this.position[1] - BORDER_HEIGHT / 2,
                SIZE[0] + BORDER_HEIGHT,
                SIZE[1] + BORDER_HEIGHT, ROUNDED + BORDER_HEIGHT, ROUNDED + BORDER_HEIGHT);

        g.setColor(Color.WHITE);
        g.fillRoundRect(this.position[0], this.position[1], SIZE[0], SIZE[1], ROUNDED, ROUNDED);
        g.setFont(Fonts.text().deriveFont(Font.PLAIN, SIZE[1] - 30));
        g.setColor(Color.BLACK);
        int[] pos = FontUtil.getCenteredPos(SIZE[0], SIZE[1], g.getFontMetrics(), text);
        g.drawString(text, this.position[0] + pos[0], this.position[1] + pos[1]);
        // System.out.println(text);
    }

    @Override
    public int getZOrder() {
        return ZOrders.SETTINGS_PANEL_ELEMENTS;
    }

}
