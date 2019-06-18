package gameobject;

import collision.HitListener;
import settings.Counter;
import game.GameLevel;

/**
 * The type Block remover.
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;


    /**
     * Instantiates a new Block remover.
     *
     * @param game          the game
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeFromGame(this.game);
            beingHit.removeHitListener(this);
            remainingBlocks.decrease(1);
        }
    }
}
