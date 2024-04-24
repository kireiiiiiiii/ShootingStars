/**
 * Author: Matěj Šťastný
 * Date created: 4/23/2024
 * Github link: https://github.com/kireiiiiiiii/TargetGame
 * 
 * TODO: Class header
 */

import java.awt.Dimension;
import java.util.Random;

public class Position {

    public double x;
    public double y;

    /**
     * Constructor for creating a new {@code Position} instance with initial
     * {@code x} and {@code y} values.
     * 
     * @param x - x value of the position object.
     * @param y - y value of the position object.
     */
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Empty constructor, sets both {@code x} and {@code y} values to zero
     */
    public Position() {
        this.x = 0;
        this.y = 0;
    }

    /**
     * Creates a new {@code Position} object with multiplied {@code x} and {@code y}
     * of the object the method was called on by {@code value}.
     * 
     * @param value - value that the Position is multiplied by.
     * @return new {@code Position} object.
     */
    public Position getMultiplied(double value) {
        return new Position(this.x * value, this.y * value);
    }

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
