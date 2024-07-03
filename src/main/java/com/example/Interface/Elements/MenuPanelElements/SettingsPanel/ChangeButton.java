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

package com.example.Interface.Elements.MenuPanelElements.SettingsPanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import com.example.Common.Language;
import com.example.Constants.Colors;
import com.example.Constants.GameDialogue;
import com.example.Constants.Textures;
import com.example.Constants.ZOrders;
import com.example.Interface.Interactable;
import com.example.Interface.MenuPanel;
import com.example.Interface.MenuScreenMode;
import com.example.Interface.Renderable;
import com.example.Tools.ImageUtil;

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

    private MenuPanel owner;
    private int[] position;
    private Image texture;
    private boolean facingLeft;

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
    public ChangeButton(MenuPanel owner, int[] position, boolean facingLeft) {
        this.facingLeft = facingLeft;
        this.owner = owner;
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
    public void refresh(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRoundRect(this.position[0] - BORDER_HEIGHT / 2, this.position[1] - BORDER_HEIGHT / 2,
                SIZE[0] + BORDER_HEIGHT,
                SIZE[1] + BORDER_HEIGHT, ROUNDED + BORDER_HEIGHT, ROUNDED + BORDER_HEIGHT);
        g.setColor(Colors.MAIN_PINK);
        g.fillRoundRect(this.position[0], this.position[1], SIZE[0], SIZE[1], ROUNDED, ROUNDED);
        g.drawImage(this.texture, this.position[0] + ICON_PADDING, this.position[1] + ICON_PADDING,
                this.owner.getFocusCycleRootAncestor());
    }

    @Override
    public int getZOrder() {
        return ZOrders.SETTINGS_PANEL_ELEMENTS;
    }

    /////////////////
    // Interact
    ////////////////

    @Override
    public MenuScreenMode getInteract() {
        Language[] languages = Language.values();
        int languageIndex = 0;
        for (int i = 0; i < languages.length; i++) {
            if (languages[i] == GameDialogue.getCurrentLanguage()) {
                languageIndex = i;
            }
        }

        if (facingLeft) {
            languageIndex--;
            if (languageIndex < 0) {
                languageIndex = languages.length - 1;
            }
        } else {
            languageIndex++;
            if (languageIndex >= languages.length) {
                languageIndex = 0;
            }
        }
        this.owner.setLanguage(languages[languageIndex]);
        return null;
    }

    @Override
    public boolean wasInteracted(MouseEvent e) {
        int x = e.getX() - position[0];
        int y = e.getY() - position[1];
        return x <= SIZE[0] && y <= SIZE[1] && x > 0 && y > 0;
    }

}
