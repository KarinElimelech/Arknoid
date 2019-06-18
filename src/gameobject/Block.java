package gameobject;

import biuoop.DrawSurface;

import collision.Collidable;
import collision.HitListener;
import collision.HitNotifier;
import collision.Sprite;
import reader.Stroke;
import settings.Counter;
import game.GameLevel;
import settings.Velocity;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

/**
 * the main class for Block.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private Sprite background;
    private Map<Integer, Sprite> multipleBackgroung;
    private Counter numbersOfHit;
    private List<HitListener> hitListeners;
    private Stroke stroke;

    /**
     * Instantiates a new Block.
     *
     * @param rect the rect
     */
    public Block(Rectangle rect) {
        this.rect = rect;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Gets rect.
     *
     * @return the rect
     */
    public Rectangle getRect() {
        return rect;
    }

    /**
     * @return this.rect - returns the rectangel.
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Sets numbers of hit.
     *
     * @param hitCounter the hit counter
     */
    public void setNumbersOfHit(int hitCounter) {
        Counter c = new Counter();
        c.increase(hitCounter);
        this.numbersOfHit = c;
    }

    /**
     * @param collisionPoint  - were the collision occur.
     * @param currentVelocity - the current v.
     * @param hitter          -the ball that hits.
     * @return velocity - the new velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double epsi = 0.00000001;
        double dx = currentVelocity.getDx(), dy = currentVelocity.getDy();

        //if the collision point is it the sides of the rectangle
        if (Math.abs(collisionPoint.getX() - rect.getLeftLine().start().getX()) < epsi
                || Math.abs(collisionPoint.getX() - rect.getRightLine().start().getX()) < epsi) {
            dx = -1 * (dx);
        }
        //if the collision is in the up or down lines of the rectangle
        if (Math.abs(collisionPoint.getY() - rect.getUpLine().start().getY()) < epsi
                || Math.abs(collisionPoint.getY() - rect.getDownLine().start().getY()) < epsi) {
            dy = -1 * (dy);
        }
        if (this.getHitPoints() > 0) {
            this.numbersOfHit.decrease(1);
        }
        notifyHit(hitter);
        return new Velocity(dx, dy);
    }

    /**
     * Sets color.
     *
     * @param c the c
     */
    public void setBackground(Sprite c) {
        this.background = c;
    }

    /**
     * Sets multiple backgroung.
     *
     * @param backgroungs the multiple backgroung
     */
    public void setMultipleBackgroung(Map<Integer, Sprite> backgroungs) {
        this.multipleBackgroung = backgroungs;
    }

    /**
     * Sets stroke.
     *
     * @param s the s
     */
    public void setStroke(Stroke s) {
        this.stroke = s;
    }

    /**
     * @param surface - the surface we draw on.
     */
    public void drawOn(DrawSurface surface) {
        if (this.multipleBackgroung != null
                && this.multipleBackgroung.containsKey(this.numbersOfHit.getValue())) {
            this.multipleBackgroung.get(this.numbersOfHit.getValue()).drawOn(surface);
        } else if (this.background != null) {
            this.background.drawOn(surface);
        } else {
            throw new RuntimeException("no background for block");
        }
        if (this.stroke != null) {
            this.stroke.drawOn(surface);
        }
    }

    /**
     * does nothing for now.
     *
     * @param dt - the move in time.
     */
    public void timePassed(double dt) {

    }

    /**
     * Add to game.
     *
     * @param game the game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
        game.addCollidable(this);
    }

    /**
     * Remove from game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }


    /**
     * @param hl the listener
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);

    }

    /**
     * @param hl the hit listener
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * @param hitter the ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);

        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Get hit points int.
     *
     * @return the int
     */
    public int getHitPoints() {
        return this.numbersOfHit.getValue();
    }
}
