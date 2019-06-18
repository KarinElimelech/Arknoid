package game;

import animation.KeyPressStoppableAnimation;
import animation.PauseScreen;
import animation.Animation;
import animation.AnimationRunner;
import animation.CountdownAnimation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collision.Collidable;
import collision.Sprite;
import collision.SpriteCollection;
import gameobject.Point;
import gameobject.Rectangle;
import gameobject.Block;
import gameobject.Paddle;
import gameobject.Ball;
import gameobject.BallRemover;
import gameobject.BlockRemover;
import animation.FillBackground;
import settings.Counter;
import settings.LivesIndicator;
import scores.ScoreIndicator;
import settings.GameEnvironment;
import scores.ScoreTrackingListener;

import java.util.ArrayList;

/**
 * the main class GameLevel.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class GameLevel implements Animation {
    //level
    private LevelInformation levelInformation;
    //class
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Paddle paddle;
    //private biuoop.GUI gui;
    private PrintingHitListener print;
    private ScoreTrackingListener scoreTrackingListener;
    private AnimationRunner runner;
    //parameters
    private Block downFrame;
    private boolean running;
    private Point upperLeftPaddle;
    private KeyboardSensor keyboard;
    //counters
    private Counter blockCount;
    private Counter ballCount;
    private Counter scoreCount;
    private Counter livesCount;
    //removers
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    //const
    private static final int BOUNDARY_SIZE = 20;
    private static final int FRAME_HIGHT = 600;
    private static final int FRAME_WIDTH = 800;


    /**
     * Instantiates a new Game.
     *
     * @param levelInformation the level information
     * @param k                the k
     * @param a                the a
     * @param livesCount       the lives count
     * @param scoreCount       the score count
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor k, AnimationRunner a
            , Counter livesCount, Counter scoreCount) {
        this.livesCount = livesCount;
        this.scoreCount = scoreCount;
        this.levelInformation = levelInformation;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.blockCount = new Counter();
        this.ballCount = new Counter();
        this.runner = a;
        this.keyboard = k;
    }

    /**
     * Add collidable.
     *
     * @param c the collideable object
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add sprite.
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Remove sprite.
     *
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Remove collidable.
     *
     * @param c the collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Get down frame block.
     *
     * @return the block
     */
    public Block getDownFrame() {
        return this.downFrame;
    }

    /**
     * @return t or f.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Gets lives.
     *
     * @return the lives
     */
    public boolean getLives() {
        return this.livesCount.getValue() != 0;
    }

    /**
     * Is there blocks boolean.
     *
     * @return ttue or false
     */
    public boolean isThereBlocks() {
        return this.blockCount.getValue() != 0;
    }

    /**
     * @param startX - start point.
     * @param startY - start point.
     * @param size   - of frame.
     */
    private void createFrame(int startX, int startY, int size) {
        for (int i = 0; i < size; i += 10) {
            Point start = new Point(startX + i, startY);
            Block b = new Block(new Rectangle(start,
                    size / 10, 10));
            b.addToGame(this);
            b.setNumbersOfHit(-1);
            b.setBackground(new FillBackground("color(RGB(142,0,0))", start, size / 10, 10));
        }
        Block block = new Block(new Rectangle(new Point(20, 30), 30, 30));
        block.addToGame(this);
        block.setNumbersOfHit(-1);
        block.setBackground(new FillBackground(
                "color(RGB(142,0,0))", new Point(20, 30), 1, 1));
        Block bl = new Block(new Rectangle(new Point(770, 30), 30, 30));
        bl.addToGame(this);
        bl.setNumbersOfHit(-1);
        bl.setBackground(new FillBackground(
                "color(RGB(142,0,0))", new Point(770, 30), 1, 1));
    }


    /**
     * Create boundary block.
     */
    public void createBoundaryBlock() {
        ArrayList<Block> blocksArr = new ArrayList<>();
        Block b1, b2, b3, b4;
        b1 = new Block(new Rectangle(new Point(0, BOUNDARY_SIZE),
                FRAME_WIDTH, BOUNDARY_SIZE));
        createFrame(0, 20, FRAME_WIDTH);
        b2 = new Block(new Rectangle(new Point(0, BOUNDARY_SIZE)
                , BOUNDARY_SIZE, FRAME_HIGHT - BOUNDARY_SIZE));
        b3 = new Block(new Rectangle(new Point(FRAME_WIDTH - BOUNDARY_SIZE, BOUNDARY_SIZE)
                , BOUNDARY_SIZE, FRAME_HIGHT - BOUNDARY_SIZE));
        b4 = new Block(new Rectangle(new Point(0, FRAME_HIGHT), FRAME_WIDTH, BOUNDARY_SIZE));
        blocksArr.add(b1);
        blocksArr.add(b2);
        blocksArr.add(b3);
        blocksArr.add(b4);
        for (Block block : blocksArr) {
            block.addToGame(this);
            block.setNumbersOfHit(-1);
            block.setBackground(new FillBackground("color(RGB(95,158,160))"
                    , block.getRect().getUpperLeft(),
                    (int) block.getRect().getWidth(), (int) block.getRect().getHeight()));
        }
        this.downFrame = b4;
        b4.addHitListener(ballRemover);
    }

    /**
     * Create block.
     */
    public void createBlock() {
        blockCount.increase(this.levelInformation.numberOfBlocksToRemove());
        for (Block block : this.levelInformation.blocks()) {
            block.addToGame(this);
            block.addHitListener(this.blockRemover);
            block.addHitListener(scoreTrackingListener);
        }
        Block bl = new Block(new Rectangle(new Point(20, 20), 30, 30));
        bl.addToGame(this);
        bl.setNumbersOfHit(-1);
        bl.setBackground(new FillBackground(
                "color(RGB(142,0,0))", new Point(0, 0), 1, 1));
        Block block = new Block(new Rectangle(new Point(750, 35), 30, 13));
        block.addToGame(this);
        block.setNumbersOfHit(-1);
        block.setBackground(new FillBackground(
                "color(RGB(142,0,0))", new Point(0, 0), 1, 1));
    }

    /**
     * Create paddle.
     */
    public void createPaddle() {
        if (this.paddle != null) {
            removeSprite(this.paddle);
        }
        Point pointLeftPaddle = new Point(FRAME_WIDTH / 2
                - 0.5 * this.levelInformation.paddleWidth()
                , FRAME_HIGHT - BOUNDARY_SIZE);
        this.upperLeftPaddle = pointLeftPaddle;
        Rectangle p = new Rectangle(pointLeftPaddle,
                this.levelInformation.paddleWidth(), BOUNDARY_SIZE / 2);
        Paddle thePaddle = new Paddle(p, this.levelInformation.paddleSpeed()
                , FRAME_WIDTH - BOUNDARY_SIZE);
        thePaddle.paddleSize(this.levelInformation.paddleWidth(), BOUNDARY_SIZE / 2);
        thePaddle.setKeyboard(this.keyboard);
        thePaddle.addToGame(this);
        this.paddle = thePaddle;
    }

    /**
     * create ball.
     */
    private void createBall() {
        for (int i = 0; i < this.levelInformation.numberOfBalls(); i++) {
            Ball ball = new Ball(new Point((this.upperLeftPaddle.getX()
                    + this.levelInformation.paddleWidth() / 2)
                    , this.upperLeftPaddle.getY() - 7), 7);
            ball.setGameEnvironment(environment);
            ball.addToGame(this);
            ball.setVelocity(this.levelInformation.initialBallVelocities().get(i));
            ballCount.increase(1);
        }
    }


    /**
     * Initialize the game.
     */
    public void initialize() {
        this.blockRemover = new BlockRemover(this, this.blockCount);
        this.ballRemover = new BallRemover(this, this.ballCount);
        this.scoreTrackingListener = new ScoreTrackingListener(this.scoreCount);
        ScoreIndicator scoreIndicator = new ScoreIndicator(scoreCount);
        this.print = new PrintingHitListener();
        LivesIndicator livesIndicator = new LivesIndicator(livesCount);
        addSprite(this.levelInformation.getBackground());
        LevelName levelName = new LevelName(this.levelInformation.levelName());
        scoreIndicator.addToGame(this);
        addSprite(levelName);
        livesIndicator.addToGame(this);
        this.running = true;
        createBlock();
        createBoundaryBlock();

    }

    /**
     * place ball and paddle.
     */
    private void createBallsOnTopOfPaddle() {
        createPaddle();
        createBall();
    }

    /**
     * @param d  the drwqsurface
     * @param dt the dt
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, keyboard.SPACE_KEY
                    , new PauseScreen()));
        }
        this.levelInformation.getBackground();
        this.scoreCount = this.scoreTrackingListener.getCurrentScore();
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        if (blockCount.getValue() == 0) {
            this.scoreCount.increase(100);
            this.running = false;
        }
        if (ballCount.getValue() == 0) {
            this.livesCount.decrease(1);
            this.running = false;
        }
    }

    /**
     * Play one turn.
     */
// Run the game -- start the animation loop.
    public void playOneTurn() {
        createBallsOnTopOfPaddle();
        this.running = true;
        this.runner.run(new CountdownAnimation(2, 3, sprites));
        this.runner.run(this);
    }
}