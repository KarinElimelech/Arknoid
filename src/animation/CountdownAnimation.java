package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import collision.SpriteCollection;

import java.awt.Color;

/**
 * The type Countdown animation.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class CountdownAnimation implements Animation {

    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private int countForStop = 0;
    private int count;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.count = countFrom;
        this.gameScreen = gameScreen;
    }

    /**
     * count down.
     *
     * @param d  the drawsurface
     * @param dt the move in time.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        gameScreen.drawAllOn(d);
        Sleeper sleeper = new Sleeper();
        int duration = (int) (numOfSeconds * 500) / count;
        d.setColor(new Color(128, 128, 0));
        if (this.countFrom != 0) {
            d.drawText(340, 400, Integer.toString(countFrom), 200);
        } else {
            d.drawText(180, 400, "GO!", 200);
        }
        if (countForStop == 0) {
            countForStop++;
        } else {
            sleeper.sleepFor(duration);
            countFrom--;
        }
    }

    /**
     * @return true or false
     */
    public boolean shouldStop() {
        return this.countFrom < -1;
    }
}
