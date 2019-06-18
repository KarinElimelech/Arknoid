package collision;

import biuoop.DrawSurface;
import gameobject.Rectangle;
import settings.Velocity;
import gameobject.Point;
import gameobject.Ball;

/**
 * the main interface for Collidable.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public interface Collidable {

    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */
// Return the "collision shape" of the object.
    Rectangle getCollisionRectangle();

    /**
     * Hit velocity.
     *
     * @param hitter          the hitter
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @return the velocity
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

    /**
     * Draw on.
     *
     * @param surface the surface
     */
    void drawOn(DrawSurface surface);
}
