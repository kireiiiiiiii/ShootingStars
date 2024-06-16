/*
 * Author: Matěj Šťastný
 * Date created: 6/13/2024
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

package com.example.Interface;

import javax.swing.*;
import com.example.Game;
import com.example.Constants.Textures;
import com.example.Game.PanelType;
import com.example.Interface.Elements.Backround;
import com.example.Interface.MenuPanelElements.MenuButton;
import com.example.Interface.MenuPanelElements.MenuScreen;
import com.example.Interface.MenuPanelElements.PopUpPanelWindget;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.awt.*;

/**
 * Main menu {@code JPanel} class.
 * 
 */
public class MenuPanel extends JPanel implements KeyListener, MouseListener, MouseMotionListener {

    /////////////////
    // Varibles
    ////////////////

    private Game game;
    private JFrame owner;
    private ArrayList<Renderable> mainRenderables;
    private ArrayList<Renderable> settingsRenderables;
    private ArrayList<Renderable> linksRenderables;
    private ArrayList<Interactable> mainInteractables;
    private ArrayList<Interactable> settingsInteractables;
    private ArrayList <Interactable> linksInteractables;
    private MenuScreenMode screenMode = MenuScreenMode.MAIN;

    /////////////////
    // Constructors
    ////////////////

    /**
     * Default constructor.
     * 
     * @param g     - {@code Game} object using this panel.
     * @param owner - {@code JFrame} owning this panel.
     */
    public MenuPanel(Game g, JFrame owner) {
        this.game = g;
        this.owner = owner;
        addKeyListener(this);
        addMouseListener(this);
        addMouseMotionListener(this);
        setFocusable(true);
        requestFocusInWindow();
        setUpWidgets();
    }

    /////////////////
    // JPanel override
    ////////////////

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;

        // Sort the list based on the ZLayer using a Comparator
        Collections.sort(this.mainRenderables, new Comparator<Renderable>() {
            @Override
            public int compare(Renderable r1, Renderable r2) {
                return Integer.compare(r2.getZOrder(), r1.getZOrder());
            }
        });

        // Render the list onto the screen
        for (Renderable r : this.mainRenderables) {
            r.refresh(g);
        }

        // If the links menu is open, render the widgets for it as an overlay
        if (this.screenMode == MenuScreenMode.LINKS) {
            Collections.sort(this.linksRenderables, new Comparator<Renderable>() {
                @Override
                public int compare(Renderable r1, Renderable r2) {
                    return Integer.compare(r2.getZOrder(), r1.getZOrder());
                }
            });
            // Render the list onto the screen
            for (Renderable r : this.linksRenderables) {
                r.refresh(g);
            }
        }

        // If the settigns are open, render the settings widgets for it as an overlay
        else if (this.screenMode == MenuScreenMode.SETTINGS) {
            Collections.sort(this.settingsRenderables, new Comparator<Renderable>() {
                @Override
                public int compare(Renderable r1, Renderable r2) {
                    return Integer.compare(r2.getZOrder(), r1.getZOrder());
                }
            });
            // Render the list onto the screen
            for (Renderable r : this.settingsRenderables) {
                r.refresh(g);
            }
        }

    }

    /*
     * Size methods return the measurments of the owning {@JFrame} object.
     * Methods return zero, if owner wasn't initialized (is {@code null}).
     */

    @Override
    public int getWidth() {
        return this.owner == null ? 0 : this.owner.getWidth();
    }

    @Override
    public int getHeight() {
        return this.owner == null ? 0 : this.owner.getHeight();
    }

    @Override
    public Dimension getSize() {
        return new Dimension(getWidth(), getHeight());
    }

    /////////////////
    // Mouse events
    ////////////////

    @Override
    public void mouseDragged(MouseEvent e) {
        this.game.mouseDragged(e, PanelType.MENU);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.game.mouseMoved(e, PanelType.MENU);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        this.game.mouseClicked(e, PanelType.MENU);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.game.mousePressed(e, PanelType.MENU);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ArrayList<Interactable> list;
        switch (this.screenMode) {
            case MAIN:
                list = this.mainInteractables;
                break;
            case LINKS:
                list = this.linksInteractables;
                break;
            case SETTINGS:
                list = this.settingsInteractables;
                break;
            default:
                list = new ArrayList<Interactable>();
                break;
        }
        for (Interactable i : list) {
            if (i.wasInteracted(e)) {
                this.screenMode = i.getScreenModeChange();
            }
        }
        this.game.mouseReleased(e, PanelType.MENU);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        this.game.mouseEntered(e, PanelType.MENU);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        this.game.mouseExited(e, PanelType.MENU);
    }

    /////////////////
    // Key Events
    ////////////////

    @Override
    public void keyPressed(KeyEvent e) {
        if (this.screenMode == MenuScreenMode.MAIN) {
            this.game.keyPressed(e, PanelType.MENU);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.game.keyReleased(e, PanelType.MENU);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        this.game.keyTyped(e, PanelType.MENU);
    }

    /////////////////
    // Widgets
    ////////////////

    /**
     * Sets up panel widgets.
     * 
     */
    private void setUpWidgets() {

        int[] size = { this.getWidth(), this.getHeight() };
        int[] linksPos = { this.getWidth() - 90, this.getHeight() - 110 };
        int[] settingsPos = { linksPos[0] - 100, linksPos[1] };

        this.mainRenderables = new ArrayList<Renderable>();
        this.settingsRenderables = new ArrayList<Renderable>();
        this.linksRenderables = new ArrayList<Renderable>();
        this.mainInteractables = new ArrayList<Interactable>();
        this.settingsInteractables = new ArrayList<Interactable>();
        this.linksInteractables = new ArrayList<Interactable>();
        MenuButton links = new MenuButton(linksPos, this);
        MenuButton settings = new MenuButton(settingsPos, this);
        PopUpPanelWindget settingsPanel = new PopUpPanelWindget(this);
        PopUpPanelWindget linksPanel = new PopUpPanelWindget(this);

        this.mainRenderables.add(new Backround(size));
        this.mainRenderables.add(new MenuScreen(size));
        this.mainRenderables.add(links);
        this.mainRenderables.add(settings);

        this.settingsRenderables.add(settingsPanel);
        
        this.linksRenderables.add(linksPanel);

        this.mainInteractables.add(links);
        this.mainInteractables.add(settings);

        this.settingsInteractables.add(settingsPanel);

        this.linksInteractables.add(linksPanel);


        links.setTexture(Textures.LINK_ICON);
        settings.setTexture(Textures.SETTINGS_ICON);

        links.setTriggerMode(MenuScreenMode.LINKS);
        settings.setTriggerMode(MenuScreenMode.SETTINGS);

    }

}
