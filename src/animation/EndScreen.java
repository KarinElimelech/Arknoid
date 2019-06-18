package animation;
import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The type End screen.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class EndScreen implements Animation {
    private boolean win;
    private int score;

    /**
     * Instantiates a new End screen.
     *
     * @param win   the win
     * @param score the score
     */
    public EndScreen(boolean win, int score) {
        this.win = win;
        this.score = score;
    }

    /**
     *
     * @param d  the drwqsurface
     * @param dt the dt
     */
    public void doOneFrame(DrawSurface d, double dt) {
        if (!this.win) {
            d.setColor(Color.BLACK);
            d.fillRectangle(20, 20, 760, 580);
            d.setColor(new Color(255, 182, 193));
            String note = "Game Over. Your score is " + Integer.toString(this.score);
            d.drawText(100, 300, note, 30);
        } else {
            d.setColor(new Color(255, 105, 180));
            d.fillRectangle(20, 20, 760, 580);
            d.setColor(new Color(255, 228, 196));
            String note = "You Win! Your score is " + Integer.toString(this.score);
            d.drawText(100, 100, note, 30);
        }
    }

    /**
     * @return true or false.
     */
    public boolean shouldStop() {
        return false;
    }

}
