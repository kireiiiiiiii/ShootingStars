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

import com.example.Common.Vec2D;
import com.example.Constants.Fonts;
import com.example.Interface.Renderable;
import com.example.Tools.FontUtil;

/**
 * A {@code Renderable} game over screen.
 * 
 */
public class GameOverScreen implements Renderable {

    private final Color MAIN_TEXT_COLOR = Color.BLACK;
    private final Color SUBTEXT_COLOR = Color.RED;
    private final String MAIN_MESSAGE = "GAME OVER";
    private final String SUB_MESSAGE = "Press R to restart";

    private int currScore;
    private int topScore;
    private int[] size;

    /**
     * Default constructor.
     * 
     * @param size - size of the game over screen, commonly the size of the owning
     *             {@code JPanel}.
     */
    public GameOverScreen(int[] size) {
        this.size = size;
        currScore = -1;
        topScore = -1;
    }

    @Override
    public void refresh(Graphics2D g) {
        
        FontMetrics fm;
        Vec2D origin;
        int[] originArr;
        int x;
        int y;
        int sideTextOffset;

        // Paints the main message
        g.setColor(this.MAIN_TEXT_COLOR);
        g.setFont(Fonts.HEADING.deriveFont(Font.BOLD, 80));
        fm = g.getFontMetrics();
        originArr = FontUtil.getCenteredPos(this.size[0], this.size[1], fm, MAIN_MESSAGE);
        origin = new Vec2D(originArr[0], originArr[1]);
        x = origin.getIntX();
        y = origin.getIntY();
        sideTextOffset = fm.getHeight();
        g.drawString(MAIN_MESSAGE, x, y);

        // Paints the smaller bottom message
        g.setColor(SUBTEXT_COLOR);
        g.setFont(Fonts.HEADING.deriveFont(Font.PLAIN, 40));
        fm = g.getFontMetrics();
        originArr = FontUtil.getCenteredPos(this.size[0], this.size[1], fm, SUB_MESSAGE);
        origin = new Vec2D(originArr[0], originArr[1]);
        x = origin.getIntX();
        y = origin.getIntY();
        g.drawString(SUB_MESSAGE, x, y + sideTextOffset);
    }

}
