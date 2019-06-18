package collision;

import gameobject.Ball;
import gameobject.Block;

/**
 * The interface Hit listener.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public interface HitListener {
    /**
     * Hit event.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
// This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Ball that's doing the hitting.
    void hitEvent(Block beingHit, Ball hitter);
}