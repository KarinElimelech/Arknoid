package animation;

import biuoop.DrawSurface;

/**
 * The interface Animation.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public interface Animation {
    /**
     * Do one frame.
     *
     * @param d  the drwqsurface
     * @param dt the dt
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * Should stop boolean.
     *
     * @return true or false
     */

    boolean shouldStop();

}
