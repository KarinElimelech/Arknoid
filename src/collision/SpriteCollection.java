package collision;

import biuoop.DrawSurface;

import java.util.List;
import java.util.ArrayList;

/**
 * the main class for SpriteCollection.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class SpriteCollection {
    private List<Sprite> spritesList;

    /**
     * Instantiates a new Sprite collection.
     */
    public SpriteCollection() {
        this.spritesList = new ArrayList<Sprite>();
    }

    /**
     * Add sprite.
     *
     * @param s the s
     */
    public void addSprite(Sprite s) {
        spritesList.add(s);
    }

    /**
     * Remove sprite.
     *
     * @param s the s
     */
    public void removeSprite(Sprite s) {
        this.spritesList.remove(s);
    }

    /**
     * Gets sprites list.
     *
     * @return the sprites list
     */
    public List<Sprite> getSpritesList() {
        return spritesList;
    }

    /**
     * Notify all time passed.
     *
     * @param dt the dt
     */
// call timePassed() on all sprites.
    public void notifyAllTimePassed(double dt) {
        if (spritesList.isEmpty()) {
            return;
        }
        ArrayList<Sprite> sl = new ArrayList<Sprite>(this.spritesList);
        for (Sprite sprite : sl) {
            sprite.timePassed(dt);
        }
    }

    /**
     * Draw all on.
     *
     * @param d the d
     */
// call drawOn(d) on all sprites.
    public void drawAllOn(DrawSurface d) {
        if (spritesList.isEmpty()) {
            return;
        }
        for (int i = 0; i < spritesList.size(); i++) {
            spritesList.get(i).drawOn(d);
        }
    }


}
