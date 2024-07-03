/*
 * Author: Matěj Šťastný
 * Date created: 6/16/2024
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

package com.example.Interface.Elements.MenuPanelElements.LinksPanel;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import com.example.Common.Links;
import com.example.Constants.Textures;
import com.example.Constants.ZOrders;
import com.example.Interface.Interactable;
import com.example.Interface.MenuScreenMode;
import com.example.Interface.Renderable;
import com.example.Tools.ImageUtil;

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
    public InstagramLink(JPanel owner, int[] position) {
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
        g.drawImage(ImageUtil.scaleImage(Textures.INSTAGRAM_LOGO, SIZE[0], SIZE[1]), position[0], position[1],
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
        Links.openURL(Links.INSTAGRAM);
        return null;
    }

    @Override
    public boolean wasInteracted(MouseEvent e) {
        int x = e.getX() - position[0];
        int y = e.getY() - position[1];
        return x <= SIZE[0] && y <= SIZE[1] && x > 0 && y > 0;
    }

}
