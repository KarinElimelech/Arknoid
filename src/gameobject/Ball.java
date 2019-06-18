package gameobject;

import biuoop.DrawSurface;
import collision.Sprite;
import collision.CollisionInfo;
import settings.GameEnvironment;
import game.GameLevel;
import settings.Velocity;

import java.awt.Color;


/**
 * the main class for Ball.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class Ball implements Sprite {
    private Point center;
    private int r;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;


    /**
     * the constructor gets the center point,radius and color and apply them.
     *
     * @param center - center point of the ball.
     * @param r      - radius of the ball.
     */
    public Ball(Point center, int r) {
        this.center = center;
        this.r = r;

    }

    /**
     * Sets game environment.
     *
     * @param environment the environment
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.gameEnvironment = environment;
    }

    /**
     * Gets x.
     *
     * @return x - the x value of the center point
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y.
     *
     * @return y - the y value of the center point.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets size.
     *
     * @return r - the radius of the ball.
     */
    public int getSize() {
        return this.r;
    }

    /**
     * receive the velocity and set it as the velocity of the ball.
     *
     * @param v - velocity
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Sets velocity.
     *
     * @param dx - the change in velocity in x.
     * @param dy - the change in velocity in y.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Gets velocity.
     *
     * @return velocity - the v value of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * check if there is velocity and the ball can move one step.
     *
     * @param dt - the move in time.
     */
    public void moveOneStep(double dt) {
        if (this.velocity == null) {
            System.out.println("Error");
            return;
        }
        Line trajectory = new Line(center, velocity.applyToPoint(center, dt));
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
        Velocity v = null;
        if (collisionInfo != null) {
            v = collisionInfo.collisionObject().
                    hit(this, collisionInfo.collisionPoint(), this.getVelocity());
        }
        if (v != null) {
            this.velocity = v;
        }
        this.center = this.velocity.applyToPoint(this.center, dt);
    }


    /**
     * @param surface - draw the ball on the given DrawSurface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.white);
        surface.fillCircle(this.getX(), this.getY(), r);
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), r);
    }

    /**
     * call move one step.
     *
     * @param dt - the move in time.
     */
    public void timePassed(double dt) {
        moveOneStep(dt);
    }

    /**
     * Add to game.
     *
     * @param game the game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

}