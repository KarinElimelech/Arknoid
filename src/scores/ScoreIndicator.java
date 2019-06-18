package scores;

import biuoop.DrawSurface;
import collision.Sprite;
import game.GameLevel;
import settings.Counter;

import java.awt.Color;

/**
 * The type Score indicator.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class ScoreIndicator implements Sprite {
    private Counter scoreCount;

    /**
     * Instantiates a new Score indicator.
     *
     * @param scoreCount the score count
     */
    public ScoreIndicator(Counter scoreCount) {
        this.scoreCount = scoreCount;

    }

    /**
     * @param d the drawsurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 0, 800, 30);
        String score = "Score: " + Integer.toString(this.scoreCount.getValue());
        d.setColor(Color.BLACK);
        d.drawText(360, 15, score, 20);
    }

    /**
     * @param dt the dt
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
