/**
 * Author: Matěj Šťastný
 * Date created: 4/23/2024
 * Github link: https://github.com/kireiiiiiiii/TargetGame
 * 
 * TODO: Class header
 */

public class Position {

    public double x;
    public double y;

    /**
     * Constructor for creating a new {@code Position} instance.
     * 
     * @param x - x value of the position object.
     * @param y - y value of the position object.
     */
    public Position(double x, double y) {
        this.x = x;
        this.y = y;
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

    public int getIntX() {
        int x = (int) Math.round(this.x);
        return x;
    }

    public int getIntY() {
        int y = (int) Math.round(this.y);
        return y;
    }
}
