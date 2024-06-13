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

import javax.swing.JPanel;
import com.example.Game;
import java.awt.event.*;
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
    private JPanel owner;

    /////////////////
    // Constructors
    ////////////////

    public MenuPanel(Game g, JPanel owner) {
        this.game = g;
        this.owner = owner;
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
    }

    /////////////////
    // JPanel override
    ////////////////

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g = (Graphics2D) graphics;

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

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
    
}
