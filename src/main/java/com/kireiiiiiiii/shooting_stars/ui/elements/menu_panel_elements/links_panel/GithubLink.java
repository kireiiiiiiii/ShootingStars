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
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

import com.kireiiiiiiii.shooting_stars.common.Links;
import com.kireiiiiiiii.shooting_stars.constants.Textures;
import com.kireiiiiiiii.shooting_stars.constants.ZOrders;
import com.kireiiiiiiii.shooting_stars.tools.ImageUtil;
import com.kireiiiiiiii.shooting_stars.ui.Interactable;
import com.kireiiiiiiii.shooting_stars.ui.MenuScreenMode;
import com.kireiiiiiiii.shooting_stars.ui.Renderable;

/**
 * Button, that openes the GitHub repository in the browser.
 * 
 */
public class GithubLink implements Renderable, Interactable {

    /////////////////
    // Constants
    ////////////////

    private final int[] SIZE = { 90, 90 };

    /////////////////
    // Variables
    ////////////////

    private JPanel owner;
    private int[] position;

    /////////////////
    // Constructors
    ////////////////

    /**
     * Default constructor.
     * 
     * @param owner    - owning {@code JPanel} object.
     * @param position - position.
     */
    public GithubLink(JPanel owner, int[] position) {
        this.owner = owner;
        this.position = position;
        this.position[0] = this.position[0] + SIZE[0] / 2;
        this.position[1] = this.position[1] + SIZE[1] / 2;
    }

    /////////////////
    // Render
    ////////////////

    @Override
    public void refresh(Graphics2D g) {
        g.drawImage(ImageUtil.scaleImage(Textures.GITHUB_LOGO, SIZE[0], SIZE[1]), position[0], position[1],
                this.owner.getFocusCycleRootAncestor());
    }

    @Override
    public int getZOrder() {
        return ZOrders.MENU_PANEL_BUTTONS;
    }

    /////////////////
    // Interact
    ////////////////

    @Override
    public MenuScreenMode getInteract() {
        Links.openURL(Links.GITHUB);
        return null;
    }

    @Override
    public boolean wasInteracted(MouseEvent e) {
        int x = e.getX() - position[0];
        int y = e.getY() - position[1];
        return x <= SIZE[0] && y <= SIZE[1] && x > 0 && y > 0;
    }

}
