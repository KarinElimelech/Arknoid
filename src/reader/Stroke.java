package reader;

import biuoop.DrawSurface;
import collision.Sprite;
import gameobject.Point;

import java.awt.Color;

/**
 * The type Stroke.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class Stroke implements Sprite {
    private Color color;
    private Point start;
    private int width;
    private int height;

    /**
     * Instantiates a new Stroke.
     *
     * @param color  the color
     * @param start  the start
     * @param width  the width
     * @param height the height
     */
    public Stroke(Color color, Point start, int width, int height) {
        this.color = color;
        this.start = start;
        this.width = width;
        this.height = height;
    }

    /**
     * @param d the drawsurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawRectangle((int) this.start.getX(), (int) this.start.getY(), this.width, this.height);
    }

    /**
     * @param dt the move in time.
     */
    public void timePassed(double dt) {

    }
}
