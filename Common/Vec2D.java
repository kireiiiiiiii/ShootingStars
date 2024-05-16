/*
 * Author: Matěj Šťastný
 * Date created: 4/23/2024
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

package Common;

import java.awt.Dimension;
import java.util.Random;

/**
 * Class to handle point data, has a {@code X} variable and {@code Y} variable. 
 * 
 */
public class Vec2D {

    /////////////////
    // Variables
    ////////////////

    public double x;
    public double y;

    /////////////////
    // Contructors
    ////////////////

    /**
     * Constructor for creating a new {@code Position} instance with initial
     * {@code x} and {@code y} values.
     * 
     * @param x - x value of the position object.
     * @param y - y value of the position object.
     */
    public Vec2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Empty constructor, sets both {@code x} and {@code y} values to zero
     */
    public Vec2D() {
        this.x = 0;
        this.y = 0;
    }

    /////////////////
    // Get new vector
    ////////////////

    /**
     * Creates a new {@code Position} object with multiplied {@code x} and {@code y}
     * of the object the method was called on by {@code value}.
     * 
     * @param value - value that the Position is multiplied by.
     * @return new {@code Position} object.
     */
    public Vec2D getMultiplied(double value) {
        return new Vec2D(this.x * value, this.y * value);
    }

    /////////////////
    // Modifiers
    ////////////////

    /**
     * Modifies the object variables {@code x} and {@code y} by adding the param
     * value to them.
     * 
     * @param value - value that's being added.
     */
    public void add(double value) {
        this.x += value;
        this.y += value;
    }

    /**
     * Modifies the object variables {@code x} and {@code y} by subtracting the
     * param
     * value to them.
     * 
     * @param value - value that's being added.
     */
    public void subtract(double value) {
        this.x -= value;
        this.y -= value;
    }

    /**
     * Sets the {@code x} and {@code y} values to random values between 0 and
     * maximums given
     * 
     * @param maxX - maximum {@code x} value
     * @param maxY - maximum {@code y} value
     */
    public void randomize(double maxX, double maxY) {
        Random r = new Random();
        this.x = r.nextDouble() * maxX;
        this.y = r.nextDouble() * maxY;
    }

    /////////////////
    // Accesors
    ////////////////

    /**
     * Converts the {@code x} variable into an {@code int}.
     * 
     * @return rounded {@code x} value.
     */
    public int getIntX() {
        int x = (int) Math.round(this.x);
        return x;
    }

    /**
     * Converts the {@code y} variable into an {@code int}.
     * 
     * @return rounded {@code y} value.
     */
    public int getIntY() {
        int y = (int) Math.round(this.y);
        return y;
    }

    /**
     * Rounds the {@code x} and {@code y} values and converts them into a new
     * {@code Dimension} object
     * 
     * @return new {@code Dimension} object
     */
    public Dimension toDimension() {
        return new Dimension(getIntX(), getIntY());
    }
}
