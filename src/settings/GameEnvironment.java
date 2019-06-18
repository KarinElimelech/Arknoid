package settings;

import collision.Collidable;
import collision.CollisionInfo;
import gameobject.Line;
import gameobject.Point;
import gameobject.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * the main class for GameEnviroment.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class GameEnvironment {
    private List<Collidable> collidableList;

    /**
     * Instantiates a new Game environment.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<Collidable>();
    }

    /**
     * Add collidable.
     *
     * @param c the collidable
     */
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }

    /**
     * Remove collidable.
     *
     * @param c the collidable.
     */
    public void removeCollidable(Collidable c) {
        this.collidableList.remove(c);
    }

    /**
     * Gets closest collision.
     *
     * @param trajectory the trajectory
     * @return the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        ArrayList<Point> closePointArr = new ArrayList<Point>();
        ArrayList<Collidable> collidableObjectArr = new ArrayList<Collidable>();
        if (collidableList.isEmpty()) {
            return null;
        }
        //add intersction points
        for (Collidable c : collidableList) {
            Rectangle rect = c.getCollisionRectangle();
            Point intersection = trajectory.closestIntersectionToStartOfLine(rect);
            if (intersection != null) {
                closePointArr.add(intersection);
                collidableObjectArr.add(c);
            }
        }
        if (closePointArr.isEmpty()) {
            return null;
        }
        //find the closest
        Point closestPoint = null;
        Collidable collidableObject = null;
        double min = Double.MAX_VALUE;
        for (int i = 0; i < closePointArr.size(); i++) {
            Point p = closePointArr.get(i);
            double dist = p.distance(trajectory.start());
            if (dist < min) {
                min = dist;
                closestPoint = closePointArr.get(i);
                collidableObject = collidableObjectArr.get(i);
            }
        }
        CollisionInfo info = new CollisionInfo(closestPoint, collidableObject);
        return info;
    }


}

