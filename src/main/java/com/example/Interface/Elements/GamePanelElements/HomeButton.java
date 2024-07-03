package com.example.Interface.Elements.GamePanelElements;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import com.example.Constants.Colors;
import com.example.Constants.Textures;
import com.example.Constants.ZOrders;
import com.example.Interface.Interactable;
import com.example.Interface.MenuScreenMode;
import com.example.Interface.Renderable;
import com.example.Tools.ImageUtil;
import java.awt.Image;
import javax.swing.JPanel;

public class HomeButton implements Renderable, Interactable {

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
    private JPanel owner;
    private int[] position;

    /////////////////
    // Constructor
    ////////////////

    /**
     * Default button constructor.
     * 
     * @param owner - owning {@code JPanel} object. 
     * @param position - position of the button in the render.
     */
    public HomeButton(JPanel owner, int[] position) {
        this.owner = owner;
        this.position = position;
        this.texture = Textures.HOME_ICON;
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
        return null;
    }

    @Override
    public boolean wasInteracted(MouseEvent e) {
        int x = e.getX() - position[0];
        int y = e.getY() - position[1];
        return x <= SIZE[0] && y <= SIZE[1] && x > 0 && y > 0;
    }

}
