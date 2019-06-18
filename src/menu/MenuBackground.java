package menu;

import biuoop.DrawSurface;
import collision.Sprite;

import java.awt.Color;

/**
 * The type Menu background.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class MenuBackground implements Sprite {
    /**
     * @param d the drawsurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(new Color(102, 205, 170));
        d.fillRectangle(20, 20, 760, 580);
    }

    /**
     * @param dt the move in time.
     */
    public void timePassed(double dt) {

    }
}
