package menu;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collision.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Menu animation.
 *
 * @param <T> the type parameter
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class MenuAnimation<T> implements Menu<T> {
    private List<Selection<T>> selcetions;
    private List<Selection<Menu<T>>> subMenu;
    private KeyboardSensor keyboardSensor;
    private Sprite background;
    private T val;
    private Menu<T> menuStatus;
    private boolean isAlreadyPressed;
    private boolean subPressed;

    /**
     * Instantiates a new Menu animation.
     *
     * @param keyboardSensor the keyboard sensor
     * @param backgraund     the backgraund
     */
    public MenuAnimation(KeyboardSensor keyboardSensor, Sprite backgraund) {
        this.selcetions = new ArrayList<Selection<T>>();
        this.subMenu = new ArrayList<Selection<Menu<T>>>();
        this.keyboardSensor = keyboardSensor;
        this.background = backgraund;
        this.menuStatus = null;
        this.isAlreadyPressed = true;
        this.subPressed = true;

    }

    /**
     * @param key       the key
     * @param message   the message
     * @param returnVal the return val
     */
    public void addSelection(String key, String message, T returnVal) {
        Selection<T> newSelection = new Selection<T>(key, message, returnVal);
        selcetions.add(newSelection);
    }

    /**
     * @param key      the key
     * @param message  the message
     * @param subMenus the sub menu
     */
    public void addSubMenu(String key, String message, Menu<T> subMenus) {
        this.subMenu.add(new Selection<Menu<T>>(key, message, subMenus));
    }

    /**
     * @param d  the drwqsurface
     * @param dt the dt
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(new Color(250, 235, 215));
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(new Color(160, 82, 45));
        d.drawText(120, 100, "Arkanoid", 30);
        int place = 150;
        int textSize = 30;
        //draw sub menu
        for (Selection<Menu<T>> menu : this.subMenu) {
            d.drawText(150, place, "(" + menu.getKey() + ")", textSize);
            d.drawText(200, place, menu.getMessage(), textSize);
            place += 50;
        }
        //draw menu
        for (Selection<T> menu : this.selcetions) {
            d.drawText(150, place, "(" + menu.getKey() + ")", textSize);
            d.drawText(200, place, menu.getMessage(), textSize);
            place += 50;
        }
        //check selection in sub menu
        for (Selection<Menu<T>> menu : this.subMenu) {
            if (this.keyboardSensor.isPressed(menu.getKey())) {
                if (!subPressed) {
                    this.menuStatus = menu.getReturnVal();
                }
            } else {
                this.subPressed = false;
            }
        }
        //check selection in menu
        for (Selection<T> menu : this.selcetions) {
            if (this.keyboardSensor.isPressed(menu.getKey())) {
                if (!isAlreadyPressed) {
                    this.val = menu.getReturnVal();
                }
            } else {
                this.isAlreadyPressed = false;
            }
        }
    }

    /**
     * @return the val.
     */
    public T getStatus() {
        return this.val;
    }

    /**
     * Sets status.
     */
    public void setStatus() {
        this.val = null;
        this.menuStatus = null;
    }

    /**
     * @return true or false.
     */
    public boolean shouldStop() {
        return (this.getStatus() != null || this.menuStatus != null);
    }
}