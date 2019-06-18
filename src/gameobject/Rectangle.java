package gameobject;

import java.util.ArrayList;

/**
 * the main class for Rectangle.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class Rectangle {
    private double width;
    private double hight;
    private Point upperLeft;
    private Point upperRight;
    private Line up;
    private Line down;
    private Line right;
    private Line left;

    /**
     * Instantiates a new Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.hight = height;
        this.width = width;
        this.upperLeft = upperLeft;
        setLines();
    }

    /**
     * Set lines.
     */
    public void setLines() {
        Point upRight = new Point(upperLeft.getX() + width, upperLeft.getY());
        this.upperRight = upRight;
        Point downLeft = new Point(upperLeft.getX(), upperLeft.getY() + hight);
        Point downRight = new Point(upRight.getX(), upRight.getY() + hight);
        this.up = new Line(upRight, upperLeft);
        this.down = new Line(downRight, downLeft);
        this.right = new Line(upRight, downRight);
        this.left = new Line(upperLeft, downLeft);
    }

    /**
     * Intersection points java . util . list.
     *
     * @param line the line
     * @return the <Point> java . util . list
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> intersectionArray = new ArrayList<Point>();
        addToList(this.getUpLine(), line, intersectionArray);
        addToList(this.getDownLine(), line, intersectionArray);
        addToList(this.getRightLine(), line, intersectionArray);
        addToList(this.getLeftLine(), line, intersectionArray);
        return intersectionArray;
    }

    /**
     * Add to list.
     *
     * @param rectangleLine the rectangle line
     * @param line          the line
     * @param intersect     the intersect <Point>
     */
    public void addToList(Line rectangleLine, Line line, java.util.List<Point> intersect) {
        Point intersectionPoint = rectangleLine.intersectionWith(line);
        if (intersectionPoint != null) {
            intersect.add(intersectionPoint);
        }
    }

    /**
     * Is ball inside rect boolean.
     *
     * @param point the point
     * @return the boolean
     */
    public boolean isBallInsideRect(Point point) {
        return (point.getX() > this.upperLeft.getX()
                && point.getX() < this.getUpperRight().getX()
                && point.getY() < this.upperLeft.getY()
                && point.getY() > this.upperLeft.getY() - this.hight);
    }

    /**
     * Get width double.
     *
     * @return the double
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Get height double.
     *
     * @return the double
     */
    public double getHeight() {
        return this.hight;
    }

    /**
     * Get upper left point.
     *
     * @return the point
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Get upper right point.
     *
     * @return the point
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * Get up line line.
     *
     * @return the line
     */
    public Line getUpLine() {
        return this.up;
    }

    /**
     * Get down line line.
     *
     * @return the line
     */
    public Line getDownLine() {
        return this.down;
    }

    /**
     * Get right line line.
     *
     * @return the line
     */
    public Line getRightLine() {
        return this.right;
    }

    /**
     * Get left line line.
     *
     * @return the line
     */
    public Line getLeftLine() {
        return this.left;
    }


}

