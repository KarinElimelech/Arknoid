package settings;

import gameobject.Point;

/**
 * the main class for Velocity.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * the constructor receives the values from the user and apply them.
     *
     * @param dx - change in velocity in x
     * @param dy - change in velocity in d
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Apply to point point.
     *
     * @param p  - the point we change.
     * @param dt the dt
     * @return point - the new point.
     */
    public Point applyToPoint(Point p, double dt) {
        double x = p.getX();
        double y = p.getY();
        return new Point(x + this.dx * dt, y + this.dy * dt);
    }

    /**
     * Gets dx.
     *
     * @return - dx value.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets dy.
     *
     * @return - dy value.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * the function calculate the velocity of the ball by using angle and speed.
     * using the angle velocity formula.
     *
     * @param angle - the angle of the ball.
     * @param speed - the speed of the ball.
     * @return - new velocity value.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dxspeed = (speed) * Math.sin(Math.toRadians(angle));
        double dyspeed = -1 * (speed) * Math.cos(Math.toRadians(angle));
        return new Velocity(dxspeed, dyspeed);
    }

}

