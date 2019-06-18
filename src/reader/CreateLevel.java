package reader;

import collision.Sprite;
import gameobject.Block;
import game.LevelInformation;
import settings.Velocity;

import java.util.List;

/**
 * The type Create level.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class CreateLevel implements LevelInformation {
    private List<Velocity> ballVelocity;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numOfBlocks;

    /**
     * Instantiates a new Create level.
     *
     * @param ballVelo    the ball velo
     * @param paddleSpeed the paddle speed
     * @param paddleWidth the paddle width
     * @param levelName   the level name
     * @param background  the background
     * @param blocks      the blocks
     * @param numOfBlocks the num of blocks
     */
    public CreateLevel(List<Velocity> ballVelo, int paddleSpeed, int paddleWidth,
                       String levelName, Sprite background, List<Block> blocks, int numOfBlocks) {
        this.ballVelocity = ballVelo;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.levelName = levelName;
        this.background = background;
        this.blocks = blocks;
        this.numOfBlocks = numOfBlocks;
    }

    /**
     * @return the ball number.
     */
    public int numberOfBalls() {
        return this.ballVelocity.size();
    }

    /**
     * @return the ball velocity.
     */
    public List<Velocity> initialBallVelocities() {
        return this.ballVelocity;
    }

    /**
     * @return the paddle speed.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * @return the paddle width.
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * @return the level name.
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * @return the background.
     */
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * @return the blocks list.
     */
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * @return the block to remove.
     */
    public int numberOfBlocksToRemove() {
        return this.numOfBlocks;
    }
}
