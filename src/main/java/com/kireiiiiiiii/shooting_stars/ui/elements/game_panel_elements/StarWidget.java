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

import java.awt.Graphics2D;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.kireiiiiiiii.shooting_stars.constants.Colors;
import com.kireiiiiiiii.shooting_stars.constants.Textures;
import com.kireiiiiiiii.shooting_stars.constants.WidgetTags;
import com.kireiiiiiiii.shooting_stars.constants.ZIndexes;
import com.kireiiiiiiii.shooting_stars.tools.ImageUtil;
import com.kireiiiiiiii.shooting_stars.ui.Renderable;

/**
 * The target widget.
 * 
 */
public class StarWidget implements Renderable {

    /////////////////
    // Constants
    ////////////////

    private final int CORRECTION = 20;

    /////////////////
    // Variables
    ////////////////

    private int radius;
    private int[] location;
    private boolean showHitbox;
    private boolean visible;

    /////////////////
    // Constructor
    ////////////////

    /**
     * Default contstructor.
     * 
     */
    public StarWidget() {
        this.showHitbox = false;
        this.radius = 10;
        this.location = new int[2];
        this.location[0] = 0;
        this.location[1] = 0;
    }

    /////////////////
    // Render
    ////////////////

    @Override
    public void render(Graphics2D g, Container img) {

        if (!visible) {
            return;
        }

        g.drawImage(ImageUtil.scaleImage(Textures.STAR, radius * 2 + CORRECTION, radius * 2 + CORRECTION),
                location[0] - radius - CORRECTION / 2, location[1] - radius - CORRECTION / 2 - 2, img);

        if (this.showHitbox) {
            g.setColor(Colors.HITBOX);
            g.fillOval(this.location[0] - this.radius, this.location[1] - this.radius,
                    this.radius * 2, this.radius * 2);

        }
    }

    @Override
    public int getZIndex() {
        return ZIndexes.TARGET;
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
        tags.add(WidgetTags.GAME);
        return tags;
    }

    /////////////////
    // Public methods
    ////////////////

    /**
     * Calculates, if the target got clicked depending on its location, and the
     * location of the click.
     * 
     * @param e - {@code MouseEvent} of the interaction.
     * @return - {@code boolean} if the target was hit.
     */
    public boolean wasClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        return Math.abs(this.location[0] - x) <= this.radius
                && Math.abs(this.location[1] - y) <= this.radius;
    }

    /////////////////
    // Modifiers
    ////////////////

    /**
     * Target location setter.
     * 
     * @param location - new location value.
     */
    public void setLocation(int[] location) {
        this.location = location;
    }

    /**
     * Radius setter.
     * 
     * @param radius - new radius value.
     */
    public void setRadius(int radius) {
        this.radius = radius;
    }

}
