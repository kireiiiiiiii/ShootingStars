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
import com.example.Game.PanelType;
import com.example.Interface.MenuPanelElements.MenuScreen;

import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

/**
 * Main menu {@code JPanel} class.
 * 
 */
public class MenuPanel extends JPanel implements KeyListener {

    /////////////////
    // Varibles
    ////////////////

    private Game game;
    private JFrame owner;
    private ArrayList<Renderable> renderables = new ArrayList<Renderable>();

    /////////////////
    // Constructors
    ////////////////

    /**
     * Default constructor.
     * 
     * @param g - {@code Game} object using this panel.
     * @param owner - {@code JFrame} owning this panel.
     */
    public MenuPanel(Game g, JFrame owner) {
        this.game = g;
        this.owner = owner;
        addKeyListener(this);
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

        for (Renderable r : this.renderables) {
            r.refresh(g);
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
    // Key Events
    ////////////////
    
    @Override
    public void keyPressed(KeyEvent e) {
        this.game.keyPressed(e, PanelType.MENU);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        this.game.keyPressed(e, PanelType.MENU);
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
    public void setUpWidgets() {

        int[] size = {this.getWidth(), this.getHeight()};

        this.renderables = new ArrayList<Renderable>();
        this.renderables.add(new MenuScreen(size));
    }
    
}
