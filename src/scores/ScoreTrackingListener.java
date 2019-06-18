package scores;

import collision.HitListener;
import gameobject.Ball;
import gameobject.Block;
import settings.Counter;

/**
 * The type Score tracking listener.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Gets current score.
     *
     * @return the current score
     */
    public Counter getCurrentScore() {
        return currentScore;
    }

    /**
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() >= 1) {
            this.currentScore.increase(5);
        }
        if (beingHit.getHitPoints() == 0) {
            this.currentScore.increase(15);
        }
    }
}
