package gameobject;

/**
 * the main class for Point.
 *
 * @author : Karin Elimelech
 * @version : 23/05/18
 */
public class Point {


    private double x;
    private double y;

    /**
     * constructor.
     *
     * @param x coordinate
     * @param y coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * return the distance of this point to the other point.
     *
     * @param other the second point
     * @return distance
     */
    public double distance(Point other) {
        double x1 = this.x;
        double x2 = other.getX();
        double y1 = this.y;
        double y2 = other.getY();
        return Math.sqrt((x1 - x2) * (x1 - x2) + ((y1 - y2) * (y1 - y2)));
    }

    /**
     * return true is the points are equal, false otherwise.
     *
     * @param other point
     * @return true if they are the same
     */
    public boolean equals(Point other) {
        return ((this.x == other.getX()) && (this.y == other.getY()));
    }

    /**
     * Return the x value of this point.
     *
     * @return x
     */
    public double getX() {
        return this.x;
    }

    /**
     * Return y value.
     *
     * @return y
     */
    public double getY() {
        return this.y;
    }

}