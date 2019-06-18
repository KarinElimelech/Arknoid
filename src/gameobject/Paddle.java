package gameobject;

import biuoop.DrawSurface;

import collision.Collidable;
import collision.Sprite;

import java.awt.Color;

import game.GameLevel;
import settings.Velocity;

/**
 * the main class for paddle.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Rectangle rect;
    private double speed;
    private double frameSize;
    private double width;
    private double hight;
    private boolean isLeftPressed;
    private boolean isRigthtPressed;

    /**
     * Instantiates a new Paddle.
     *
     * @param rect      the rect
     * @param pSpeed    the pspeed
     * @param frameSize the frame size
     */
    public Paddle(Rectangle rect, double pSpeed, double frameSize) {
        this.rect = rect;
        this.speed = pSpeed;
        this.frameSize = frameSize;
        this.isLeftPressed = true;
        this.isRigthtPressed = true;
    }

    /**
     * Paddle size.
     *
     * @param widthP the width
     * @param hightP the hight
     */
    public void paddleSize(double widthP, double hightP) {
        this.width = widthP;
        this.hight = hightP;
    }

    /**
     * Set keyboard.
     *
     * @param ks the ks
     */
    public void setKeyboard(biuoop.KeyboardSensor ks) {
        this.keyboard = ks;
    }

    /**
     * Move left.
     *
     * @param dt the dt
     */
    public void moveLeft(double dt) {
        if (this.rect.getUpperLeft().getX() < 30) {
            return;
        }
        Point newUpperLeft = new Point(this.rect.getUpperLeft().getX() - this.speed * dt,
                this.rect.getUpperLeft().getY());
        this.rect = new Rectangle(newUpperLeft, this.width, this.hight);
    }

    /**
     * Move right.
     *
     * @param dt the dt
     */
    public void moveRight(double dt) {
        if (this.rect.getUpperRight().getX() >= frameSize - 10) {
            return;
        }
        Point newUpperLeft = new Point(this.rect.getUpperLeft().getX() + this.speed * dt,
                this.rect.getUpperLeft().getY());
        this.rect = new Rectangle(newUpperLeft, this.width, this.hight);
    }

    /**
     * reset keyboard.
     * @param dt - the move in time.
     */
    public void timePassed(double dt) {
        if (keyboard.isPressed(keyboard.LEFT_KEY)) {
            moveLeft(dt);
        }
        if (keyboard.isPressed(keyboard.RIGHT_KEY)) {
            moveRight(dt);
        }
    }

    /**
     * @param d - surface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.cyan);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(),
                (int) this.rect.getUpperLeft().getY(), (int) this.rect.getWidth(),
                (int) this.rect.getHeight());
    }

    /**
     * @return this.rect - rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @param hitter          - the ball that hits.
     * @return velocity - the new velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double xVal = Math.round(collisionPoint.getX());
        double yVal = Math.round(collisionPoint.getY());
        double leftXvalue = this.rect.getUpperLeft().getX();
        double rightXvalue = leftXvalue + this.rect.getWidth();
        double ballSpeed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2)
                + Math.pow(currentVelocity.getDy(), 2));
        // Getting the 5 parts of the Paddle.
        double difference = (rightXvalue - leftXvalue) / 5;
        // New Velocity object which will be returned, finally.
        Velocity velocity = null;
        //upper hit - categorized by the 5 Parts!
        if (yVal == this.rect.getUpperLeft().getY()) {
            if (xVal >= leftXvalue && xVal < leftXvalue + 1 * difference) {
                velocity = Velocity.fromAngleAndSpeed(300, ballSpeed);
            } else if (xVal >= leftXvalue + 1 * difference && xVal < leftXvalue + 2 * difference) {
                velocity = Velocity.fromAngleAndSpeed(330, ballSpeed);
            } else if (xVal >= leftXvalue + 2 * difference && xVal < leftXvalue + 3 * difference) {
                velocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
            } else if (xVal >= leftXvalue + 3 * difference && xVal < leftXvalue + 4 * difference) {
                velocity = Velocity.fromAngleAndSpeed(30, ballSpeed);
            } else if (xVal >= leftXvalue + 4 * difference && xVal <= rightXvalue) {
                velocity = Velocity.fromAngleAndSpeed(60, ballSpeed);
            }
        }
        //sides hit
        if (xVal == rightXvalue || xVal == leftXvalue) {
            velocity = new Velocity((currentVelocity.getDx() * -1), currentVelocity.getDy());
        }
        //down hit
        if (yVal == this.rect.getUpperLeft().getY() + this.rect.getHeight()) {
            velocity = new Velocity(currentVelocity.getDx(), currentVelocity.getDy() * -1);
        }
        return velocity;
    }

    /**
     * Add to game.
     *
     * @param g the game level
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}
