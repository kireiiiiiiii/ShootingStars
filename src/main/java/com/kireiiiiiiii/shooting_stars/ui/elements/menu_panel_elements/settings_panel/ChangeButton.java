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

package com.kireiiiiiiii.shooting_stars.ui.elements.menu_panel_elements.settings_panel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.kireiiiiiiii.shooting_stars.constants.Colors;
import com.kireiiiiiiii.shooting_stars.constants.Textures;
import com.kireiiiiiiii.shooting_stars.constants.WidgetTags;
import com.kireiiiiiiii.shooting_stars.constants.ZIndexes;
import com.kireiiiiiiii.shooting_stars.tools.ImageUtil;
import com.kireiiiiiiii.shooting_stars.ui.Interactable;
import com.kireiiiiiiii.shooting_stars.ui.MenuScreenMode;
import com.kireiiiiiiii.shooting_stars.ui.Renderable;

/**
 * Basic language change button widget.
 * 
 */
public class ChangeButton implements Renderable, Interactable {

    /////////////////
    // Constants
    ////////////////

    public static final int[] SIZE = { 80, 80 };
    private static final int ROUNDED = 50;
    private static final int BORDER_HEIGHT = 10;
    private static int ICON_PADDING = 10;

    /////////////////
    // Varibles
    ////////////////

    private int[] position;
    private Image texture;
    private boolean facingLeft;
    private boolean isVisible;

    /////////////////
    // Constructors
    ////////////////

    /**
     * Default constructor.
     * 
     * @param owner      - owning {@code MenuPanel} object.
     * @param position   - position of the widget.
     * @param facingLeft - flips the texture.
     */
    public ChangeButton(int[] position, boolean facingLeft) {
        this.facingLeft = facingLeft;
        this.position = position;
        if (facingLeft) {
            this.texture = ImageUtil.scaleImage(Textures.ARROW_LEFT, SIZE[0] - ICON_PADDING * 2,
                    SIZE[1] - ICON_PADDING * 2);
        } else {
            this.texture = ImageUtil.scaleImage(Textures.ARROW_RIGHT, SIZE[0] - ICON_PADDING * 2,
                    SIZE[1] - ICON_PADDING * 2);
        }
    }

    /////////////////
    // Render
    ////////////////

    @Override
    public void render(Graphics2D g, Container img) {
        if (!this.isVisible) {
            return;
        }

        g.setColor(Color.BLACK);
        g.fillRoundRect(this.position[0] - BORDER_HEIGHT / 2, this.position[1] - BORDER_HEIGHT / 2,
                SIZE[0] + BORDER_HEIGHT,
                SIZE[1] + BORDER_HEIGHT, ROUNDED + BORDER_HEIGHT, ROUNDED + BORDER_HEIGHT);
        g.setColor(Colors.MAIN_PINK);
        g.fillRoundRect(this.position[0], this.position[1], SIZE[0], SIZE[1], ROUNDED, ROUNDED);
        g.drawImage(this.texture, this.position[0] + ICON_PADDING, this.position[1] + ICON_PADDING, img);
    }

    @Override
    public int getZIndex() {
        return ZIndexes.SETTINGS_PANEL_ELEMENTS;
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
        tags.add(WidgetTags.OPTIONS);
        return tags;
    }

    /////////////////
    // Interact
    ////////////////

    @Override
    public MenuScreenMode getInteract() {
        com.kireiiiiiiii.shooting_stars.AppMain.game.onLanguageChange(!facingLeft);
        return null;
    }

    @Override
    public boolean wasInteracted(MouseEvent e) {
        int x = e.getX() - position[0];
        int y = e.getY() - position[1];
        return x <= SIZE[0] && y <= SIZE[1] && x > 0 && y > 0;
    }

}
