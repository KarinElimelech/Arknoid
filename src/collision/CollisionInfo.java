package collision;
import gameobject.Point;

/**
 * the main class CollisionInfo.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class CollisionInfo {
    private Point collidePoint;
    private Collidable collideObject;

    /**
     * Instantiates a new Collision info.
     *
     * @param collidePoint  the collide point
     * @param collideObject the collide object
     */
    public CollisionInfo(Point collidePoint, Collidable collideObject) {
        this.collideObject = collideObject;
        this.collidePoint = collidePoint;
    }

    /**
     * Collision point point.
     *
     * @return the point
     */
    public Point collisionPoint() {
        return this.collidePoint;
    }

    /**
     * Collision object collidable.
     *
     * @return the collidable
     */
    public Collidable collisionObject() {
        return this.collideObject;
    }
}
