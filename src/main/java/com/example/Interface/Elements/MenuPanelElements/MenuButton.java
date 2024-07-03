/*
 * Author: Matěj Šťastný
 * Date created: 6/16/2024
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

package com.example.Interface.MenuPanelElements;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import com.example.Constants.Colors;
import com.example.Constants.ZOrders;
import com.example.Interface.Interactable;
import com.example.Interface.MenuScreenMode;
import com.example.Interface.Renderable;
import com.example.Tools.ImageUtil;

/**
 * Button with the menu button style.
 * 
 */
public class MenuButton implements Renderable, Interactable {

    /////////////////
    // Constants
    ////////////////

    private final int[] SIZE = { 70, 70 };
    private final int[] ICON_PADDING = { 15, 15 };
    private final int BORDER_WIDTH = 10;
    private final int ARC_WIDTH = 40;

    /////////////////
    // Variables
    ////////////////

    private Image texture;
    private int[] position;
    private JPanel owner;
    private MenuScreenMode triggerMode;

    /////////////////
    // Constructors
    ////////////////

    /**
     * Default button constructor.
     * 
     * @param pos   - position of the button.
     * @param owner - owning {@code JPanel} object.
     */
    public MenuButton(int[] pos, JPanel owner) {
        this.position = pos;
        this.owner = owner;
    }

    /////////////////
    // Render
    ////////////////

    @Override
    public void refresh(Graphics2D g) {
        g.setColor(Colors.MAIN_GRAY);
        g.fillRoundRect(this.position[0] - BORDER_WIDTH / 2, this.position[1] - BORDER_WIDTH / 2,
                SIZE[0] + BORDER_WIDTH, SIZE[1] + BORDER_WIDTH, ARC_WIDTH + BORDER_WIDTH, ARC_WIDTH + BORDER_WIDTH);
        g.setColor(Colors.MAIN_GREEN);
        g.fillRoundRect(this.position[0], this.position[1], SIZE[0], SIZE[1], ARC_WIDTH, ARC_WIDTH);

        if (this.texture != null) {
            g.drawImage(
                    ImageUtil.scaleImage(this.texture, SIZE[0] - ICON_PADDING[0], SIZE[1] - ICON_PADDING[1]),
                    position[0] + ICON_PADDING[0] / 2, position[1] + ICON_PADDING[1] / 2,
                    this.owner.getFocusCycleRootAncestor());
        }
    }

    @Override
    public int getZOrder() {
        return ZOrders.MENU_BUTTONS;
    }

    /////////////////
    // Interact
    ////////////////

    @Override
    public MenuScreenMode getInteract() {
        return this.triggerMode == null ? MenuScreenMode.MAIN : this.triggerMode;
    }

    @Override
    public boolean wasInteracted(MouseEvent e) {
        int x = e.getX() - position[0];
        int y = e.getY() - position[1];
        return x <= SIZE[0] && y <= SIZE[1] && x > 0 && y > 0;
    }

    /////////////////
    // Modifiers
    ////////////////

    /**
     * Button texture modifier. Sets the icon image displayed in the middle of the
     * button.
     * 
     * @param texture - new {@code Image} texture.
     */
    public void setTexture(Image texture) {
        this.texture = texture;
    }

    /**
     * Sets the trigger menu screen mode.
     * 
     * @param mode - new mode.
     */
    public void setTriggerMode(MenuScreenMode mode) {
        this.triggerMode = mode;
    }

}
