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

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import com.example.Common.Vec2D;
import com.example.Constants.Fonts;
import com.example.Constants.ZOrders;
import com.example.Interface.Renderable;
import com.example.Tools.FontUtil;

/**
 * Pause screen {@code Renderable}.
 * 
 */
public class PauseScreen implements Renderable {

    /////////////////
    // Constants
    ////////////////

    private final String TEXT = "Pause";

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

        g.setColor(Color.BLACK);
        g.setFont(Fonts.HEADING.deriveFont(Font.BOLD, 80));
        fm = g.getFontMetrics();
        originArr = FontUtil.getCenteredPos(size[0], size[1], fm, TEXT);
        origin = new Vec2D(originArr[0], originArr[1]);
        x = origin.getIntX();
        y = origin.getIntY();
        g.drawString(TEXT, x, y);
    }

    @Override
    public int getZOrder() {
        return ZOrders.SCREENS;
    }

}
