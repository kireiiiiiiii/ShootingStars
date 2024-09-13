/*
 * Author: Matěj Šťastný
 * Date created: 6/16/2024
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

package com.kireiiiiiiii.shooting_stars.ui.elements.menu_panel_elements.links_panel;

import java.awt.Graphics2D;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.kireiiiiiiii.shooting_stars.constants.Interact;
import com.kireiiiiiiii.shooting_stars.constants.Textures;
import com.kireiiiiiiii.shooting_stars.constants.WidgetTags;
import com.kireiiiiiiii.shooting_stars.constants.ZIndexes;
import com.kireiiiiiiii.shooting_stars.tools.ImageUtil;
import com.kireiiiiiiii.shooting_stars.ui.Interactable;
import com.kireiiiiiiii.shooting_stars.ui.Renderable;

/**
 * Button that openes my instagram account page in the browser.
 * 
 */
public class InstagramLink implements Renderable, Interactable {

    /////////////////
    // Constants
    ////////////////

    private final int[] SIZE = { 90, 90 };

    /////////////////
    // Variables
    ////////////////

    private int[] position;
    private boolean isVisible;

    /////////////////
    // Constructors
    ////////////////

    /**
     * Default constructor.
     * 
     * @param owner    - owning {@code JPanel} object.
     * @param position - position.
     */
    public InstagramLink(int[] position) {
        this.position = position;
        this.position[0] = this.position[0] + SIZE[0] / 2;
        this.position[1] = this.position[1] + SIZE[1] / 2;
    }

    /////////////////
    // Render
    ////////////////

    @Override
    public void render(Graphics2D g, Container img) {
        if (!this.isVisible) {
            return;
        }

        g.drawImage(ImageUtil.scaleImage(Textures.INSTAGRAM_LOGO, SIZE[0], SIZE[1]), position[0], position[1], img);
    }

    @Override
    public int getZIndex() {
        return ZIndexes.POPUP_PANEL_BUTTONS;
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
        tags.add(WidgetTags.LINKS);
        return tags;
    }

    /////////////////
    // Interact
    ////////////////

    @Override
    public Runnable getInteraction() {
        return Interact.INSTAGRAM;
    }

    @Override
    public boolean wasInteracted(MouseEvent e) {
        int x = e.getX() - position[0];
        int y = e.getY() - position[1];
        return x <= SIZE[0] && y <= SIZE[1] && x > 0 && y > 0;
    }

}
