/*
 * Author: Matěj Šťastný
 * Date created: 6/15/2024
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

package com.example.Interface.Elements;

import java.awt.Color;
import java.awt.Graphics2D;

import com.example.Constants.ZOrders;
import com.example.Interface.Renderable;

/**
 * Renderable backround object.
 * 
 */
public class Backround implements Renderable {

    /////////////////
    // Variables
    ////////////////

    private int[] size;
    private Color color;

    /////////////////
    // Constructors
    ////////////////

    public Backround(int[] size) {
        this.color = Color.YELLOW;
        this.size = size;
    }

    /////////////////
    // Render
    ////////////////

    @Override
    public void refresh(Graphics2D g) {
        g.setColor(this.color);
        g.fillRect(0, 0, this.size[0], this.size[1]);
    }

    @Override
    public int getZOrder() {
        return ZOrders.BACKROUND;
    }

    /////////////////
    // toString
    ////////////////

    @Override
    public String toString() {
        return "Bakcround Widget";
    }
    
}
