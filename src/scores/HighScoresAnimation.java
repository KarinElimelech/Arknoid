package scores;

import animation.Animation;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * The type High scores animation.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable highScoresTable;

    /**
     * Instantiates a new High scores animation.
     *
     * @param scores the scores
     */
    public HighScoresAnimation(HighScoresTable scores) {
        this.highScoresTable = scores;
    }

    /**
     * @param d  the drwqsurface
     * @param dt the dt
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(new Color(255, 228, 225));
        d.fillRectangle(20, 20, 760, 580);
        d.setColor(new Color(240, 128, 128));
        d.drawText(70, 70, "High Scores", 40);
        d.setColor(new Color(205, 92, 92));
        d.drawLine(160, 160, 600, 160);
        int namePlace = 190;
        d.drawText(namePlace, 140, "Player Name", 30);
        int scorePlace = 470;
        d.drawText(scorePlace, 140, "Score", 30);
        int space = 200;
        d.setColor(new Color(219, 112, 147));
        for (ScoreInfo score : this.highScoresTable.getHighScores()) {
            d.drawText(namePlace, space, score.getName(), 25);
            d.drawText(scorePlace, space, Integer.toString(score.getScore()), 25);
            space += 50;
        }
        d.setColor(new Color(160, 82, 45));
        d.drawText(50, 550, "press space to continue", 20);
    }

    /**
     * @return true or false.
     */
    public boolean shouldStop() {
        return false;
    }

}
