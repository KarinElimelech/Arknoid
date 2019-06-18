package animation;
import biuoop.DrawSurface;

/**
 * The type Pause screen.
 *
 * @author : Karin Elimelech
 * @version : 24/05/18
 */
public class PauseScreen implements Animation {

    /**
     * Instantiates a new Pause screen.
     */
    public PauseScreen() {
    }

    /**
     * @param d  the drwqsurface
     * @param dt the dt
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.drawText(10, d.getHeight() / 2,
                "paused -- press space to continue", 32);
    }

    /**
     * @return true or false.
     */
    public boolean shouldStop() {
        return false;
    }
}
