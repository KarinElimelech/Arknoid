package gameobject;

import collision.HitListener;
import settings.Counter;
import game.GameLevel;

/**
 * The type Ball remover.
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBall;


    /**
     * Instantiates a new Ball remover.
     *
     * @param game        the game
     * @param removedBall the removed ball
     */
    public BallRemover(GameLevel game, Counter removedBall) {
        this.game = game;
        this.remainingBall = removedBall;
    }

    /**
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (this.game.getDownFrame() == beingHit) {
            game.removeSprite(hitter);
            remainingBall.decrease(1);
        }
    }
}
