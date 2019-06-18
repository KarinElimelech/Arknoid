package gameobject;

/**
 * the main class for Line.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class Line {

    private Point start;
    private Point end;

    /**
     * Instantiates a new Line.
     *
     * @param start the start
     * @param end   the end
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Instantiates a new Line.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }


    /**
     * Slope double.
     *
     * @return the double
     */
    public double slope() {
        return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
    }


    /**
     * Length double.
     *
     * @return the double
     */
    public double length() {
        return this.start.distance(end);
    }


    /**
     * Middle point.
     *
     * @return the point
     */
    public Point middle() {
        double x = (this.start.getX() + this.end.getX()) / 2;
        double y = (this.start.getY() + this.end.getY()) / 2;
        return new Point(x, y);
    }


    /**
     * Start point.
     *
     * @return the point
     */
    public Point start() {
        return this.start;
    }


    /**
     * End point.
     *
     * @return the point
     */
    public Point end() {
        return this.end;
    }

    /**
     * Is intersecting boolean.
     *
     * @param other the other
     * @return the boolean
     */
    public boolean isIntersecting(Line other) {
        return (intersectionWith(other) != null);
    }


    /**
     * Intersection with point.
     *
     * @param other the other
     * @return the point
     */
    public Point intersectionWith(Line other) {
        if (this.equals(other)) {
            return null;
        }
        // checking whether one of the lines is vertical
        if (this.start().getX() == this.end().getX()) {
            Point intersect = vertical(this.start().getX(), other);
            if ((this.isPointInLine(intersect)) && (other.isPointInLine(intersect))) {
                return intersect;
            } else {
                return null;
            }
        }
        if (other.start().getX() == other.end().getX()) {
            Point intersect = vertical(other.start().getX(), this);
            if ((this.isPointInLine(intersect)) && (other.isPointInLine(intersect))) {
                return intersect;
            } else {
                return null;
            }
        }
        double mUp = this.start().getY() - this.end().getY();
        double mDown = this.start().getX() - this.end().getX();
        double m1 = mUp / mDown;
        double m2Up = other.start().getY() - other.end().getY();
        double m2Dowm = other.start().getX() - other.end().getX();
        double m2 = m2Up / m2Dowm;
        double n1 = this.start().getY() - m1 * this.start().getX();
        double n2 = other.start().getY() - m2 * other.start().getX();
        if (m1 == m2) {
            return null;
        }
        if (n1 == n2) {
            return new Point(0, n1);
        }
        if ((this.start().equals(other.start())) || (this.start().equals(other.end()))) {
            return this.start();
        }
        if ((this.end().equals(other.start())) || (this.end().equals(other.end()))) {
            return this.end();
        }
        double a = (n2 - n1) / (m1 - m2);
        double b = m1 * a + n1;
        Point intersection = new Point(a, b);
        // checking if the point is on both lines
        if (this.isPointInLine(intersection) && (other.isPointInLine(intersection))) {
            return intersection;
        }
        return null;
    }

    /**
     * Vertical point.
     *
     * @param x the x
     * @param l the l
     * @return the point
     */
    public Point vertical(double x, Line l) {
        double m = (l.start().getY() - l.end().getY()) / (l.start().getX() - l.end().getX());
        double n = l.start().getY() - m * l.start().getX();
        double y = (m * x) + n;
        return new Point(x, y);
    }

    /**
     * Is point in line boolean.
     *
     * @param point the point
     * @return the boolean
     */
    public boolean isPointInLine(Point point) {
        return point.distance(this.start) + point.distance(this.end) < this.start.distance(this.end) + 1;
    }

    /**
     * Equals boolean.
     *
     * @param other the other
     * @return the boolean
     */
    public boolean equals(Line other) {
        return (this.start.equals(other.start()) && (this.end.equals(other.end())));
    }


    /**
     * Closest intersection to start of line point.
     *
     * @param rect the rect
     * @return the point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> points = rect.intersectionPoints(this);
        if (points.isEmpty()) {
            return null;
        }
        if ((points.size() == 1)
                || (points.get(0).distance(this.start()) < points.get(1).distance(this.start()))) {
            return points.get(0);
        }
        return points.get(1);
    }
}
