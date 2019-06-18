package game;

import collision.Sprite;
import gameobject.Block;
import settings.Velocity;

import java.util.List;

/**
 * The interface Level information.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public interface LevelInformation {
    /**
     * Number of balls int.
     *
     * @return the int
     */
    int numberOfBalls();

    /**
     * Initial ball velocities list.
     *
     * @return the list
     */
    List<Velocity> initialBallVelocities();

    /**
     * Paddle speed int.
     *
     * @return the int
     */
    int paddleSpeed();

    /**
     * Paddle width int.
     *
     * @return the int
     */
    int paddleWidth();

    /**
     * Level name string.
     *
     * @return the string
     */
    String levelName();

    /**
     * Gets background.
     *
     * @return the background
     */
    Sprite getBackground();


    /**
     * Blocks list.
     *
     * @return the list
     */
    List<Block> blocks();

    /**
     * Number of blocks to remove int.
     *
     * @return the int
     */
    int numberOfBlocksToRemove();
}
