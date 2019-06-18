package animation;

import biuoop.DrawSurface;
import collision.Sprite;
import gameobject.Point;
import reader.ColorsParser;

import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.Color;
import java.io.IOException;

/**
 * The type Fill background.
 */
public class FillBackground implements Sprite {
    private Image image;
    private Color backgroundColor;
    private Point start;
    private int width;
    private int height;

    /**
     * Instantiates a new Fill background.
     *
     * @param background the background
     * @param start      the start
     * @param width      the width
     * @param hight      the hight
     */
    public FillBackground(String background, Point start, int width, int hight) {
        this.image = null;
        this.backgroundColor = null;
        this.start = start;
        this.width = width;
        this.height = hight;
        if (background.contains("image")) {
            try {
                this.image = ImageIO.read(ClassLoader.getSystemResourceAsStream(background
                        .substring(background.lastIndexOf("(") + 1,
                                background.indexOf(")"))));
            } catch (IOException e) {
                System.out.println("no pic in fillBackground");
            }
        }
        if (background.contains("color")) {
            //convert to color
            ColorsParser colorsParser = new ColorsParser();
            this.backgroundColor = colorsParser.colorFromString(background);
        }
    }

    /**
     * @param d the drawsurface.
     */
    public void drawOn(DrawSurface d) {
        //draw the frame
        if (image != null) {
            d.drawImage((int) this.start.getX(), (int) this.start.getY(), this.image);
        } else if (this.backgroundColor != null) {
            d.setColor(this.backgroundColor);
            d.fillRectangle((int) this.start.getX(), (int) this.start.getY(), this.width, this.height);
        }
    }

    /**
     * @param dt the move in time.
     */
    public void timePassed(double dt) {

    }
}
