package collision;
import biuoop.DrawSurface;

/**
 * the main interface Sprite.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public interface Sprite {
    /**
     * Draw on.
     *
     * @param d the d
     */
// draw the sprite to the screen
    void drawOn(DrawSurface d);

    /**
     * Time passed.
     *
     * @param dt the dt
     */
// notify the sprite that time has passed
    void timePassed(double dt);
}
