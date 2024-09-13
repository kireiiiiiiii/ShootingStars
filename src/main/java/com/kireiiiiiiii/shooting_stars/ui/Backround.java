/*
 * Author: Matěj Šťastný
 * Date created: 6/15/2024
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

package com.kireiiiiiiii.shooting_stars.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Container;
import java.util.ArrayList;

import com.kireiiiiiiii.shooting_stars.constants.Colors;
import com.kireiiiiiiii.shooting_stars.constants.WidgetTags;
import com.kireiiiiiiii.shooting_stars.constants.ZIndexes;
import com.kireiiiiiiii.shooting_stars.interfaces.Renderable;

/**
 * Renderable backround object.
 * 
 */
public class Backround implements Renderable {

    /////////////////
    // Variables
    ////////////////

    private int[] size;
    private Color color;
    private boolean isVisible;

    /////////////////
    // Constructors
    ////////////////

    /**
     * Default consctructor.
     * 
     * @param size - size of the screen, usually the dimensions of the owning
     *             {@code JPanel} object.
     */
    public Backround(int[] size) {
        this.color = Colors.BACKROUND;
        this.size = size;
    }

    /////////////////
    // Render
    ////////////////

    @Override
    public void render(Graphics2D g, Container img) {
        if (!this.isVisible) {
            return;
        }

        g.setColor(this.color);
        g.fillRect(0, 0, this.size[0], this.size[1]);
    }

    @Override
    public int getZIndex() {
        return ZIndexes.BACKROUND;
    }

    @Override
    public boolean isVisible() {
        return this.isVisible;
    }

    @Override
    public void hide() {
        this.isVisible = false;
    }

    @Override
    public void show() {
        this.isVisible = true;
    }

    @Override
    public ArrayList<String> getTags() {
        ArrayList<String> tags = new ArrayList<String>();
        tags.add(WidgetTags.GAME);
        tags.add(WidgetTags.GAME_OVER);
        tags.add(WidgetTags.LINKS);
        tags.add(WidgetTags.MAIN_MENU);
        tags.add(WidgetTags.OPTIONS);
        tags.add(WidgetTags.OPTIONS);
        tags.add(WidgetTags.PAUSE);
        return tags;
    }

    /////////////////
    // toString
    ////////////////

    @Override
    public String toString() {
        return "Bakcround Widget";
    }

}
