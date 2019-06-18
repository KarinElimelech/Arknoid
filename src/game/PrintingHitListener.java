package game;

import collision.HitListener;
import gameobject.Ball;
import gameobject.Block;

/**
 * The type Printing hit listener.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class PrintingHitListener implements HitListener {
    /**
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A Block with " + beingHit.getHitPoints() + " points was hit.");
    }
}
