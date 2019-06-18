package game;

import biuoop.DrawSurface;
import collision.Sprite;

import java.awt.Color;

/**
 * The type Level name.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class LevelName implements Sprite {
    /**
     * The Level.
     */
    private String level;

    /**
     * Instantiates a new Level name.
     *
     * @param level the level
     */
    public LevelName(String level) {
        this.level = level;
    }

    /**
     * @param d the drawsurface.
     */
    public void drawOn(DrawSurface d) {
        String levelNote = "Level Name: " + this.level;
        d.setColor(Color.BLACK);
        d.drawText(500, 15, levelNote, 20);
    }

    /**
     *
     * @param dt the time unit.
     */
    public void timePassed(double dt) {

    }

}
