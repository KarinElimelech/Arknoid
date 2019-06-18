package settings;

import biuoop.DrawSurface;
import collision.Sprite;
import game.GameLevel;

import java.awt.Color;

/**
 * The type Lives indicator.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class LivesIndicator implements Sprite {
    private Counter livesCount;

    /**
     * Instantiates a new Lives indicator.
     *
     * @param livesCount the lives count
     */
    public LivesIndicator(Counter livesCount) {
        this.livesCount = livesCount;
    }

    /**
     * @param d the drawsurface.
     */
    public void drawOn(DrawSurface d) {
        String lives = "lives: " + Integer.toString(this.livesCount.getValue());
        d.setColor(Color.BLACK);
        d.drawText(30, 15, lives, 20);
    }

    /**
     *
     * @param dt the move in time.
     */
    public void timePassed(double dt) {
    }

    /**
     * Add to game.
     *
     * @param game the game
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }


}
