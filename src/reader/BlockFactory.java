package reader;

import animation.FillBackground;
import collision.Sprite;
import gameobject.Block;
import gameobject.Point;
import gameobject.Rectangle;

import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Block factory.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class BlockFactory implements BlockCreator {
    private int width;
    private int height;
    private int hitPoint;
    private Sprite onlyBackground;
    private String blockName;
    private Stroke stroke;
    private Point start;
    //private Map<Integer, Sprite> fillBackground;
    private static final String REGEX_FILL = "fill-[1-9]+[0-9]*";
    private Map<String, String> settings;

    /**
     * Instantiates a new Block factory.
     *
     * @param block           the block
     * @param defaultSettings the default settings
     */
    public BlockFactory(String block, List<String> defaultSettings) {
        Map<String, String> setting = new TreeMap<String, String>();
        //this.fillBackground = new TreeMap<>();
        String[] row = block.split(" ");
        for (int k = 0; k < defaultSettings.size(); k++) {
            String[] s = defaultSettings.get(k).split(" ");
            for (int i = 0; i < s.length; i++) {
                String[] defRow = s[i].split(":");
                setting.put(defRow[0], defRow[1]);
            }
        }
        for (int i = 0; i < row.length; i++) {
            String[] set = row[i].split(":");
            setting.put(set[0], set[1]);
        }
        try {
            this.width = Integer.parseInt(setting.get("width"));
            setting.remove("width");
            this.height = Integer.parseInt(setting.get("height"));
            setting.remove("height");
            this.hitPoint = Integer.parseInt(setting.get("hit_points"));
            setting.remove("hit_points");
            this.blockName = setting.get("symbol");
            setting.remove("symbol");
        } catch (RuntimeException e) {
            System.out.println("one of the block parameters is missing");
            throw e;
        }

        this.settings = setting;
    }

    /**
     * @param setting the settings.
     * @return map.
     */
    private Map<Integer, Sprite> fill(Map<String, String> setting) {
        Map<Integer, Sprite> backgrounds = new TreeMap<Integer, Sprite>();
        for (Map.Entry<String, String> set : setting.entrySet()) {
            if (set.getKey().matches(REGEX_FILL)) {
                FillBackground fillBackground = new FillBackground(set.getValue()
                        , this.start, this.width, this.height);
                Integer numOfFill = Integer.parseInt(set.getKey()
                        .substring(set.getKey().indexOf("-") + 1));
                backgrounds.put(numOfFill, fillBackground);
            } else if (set.getKey().contains("fill")) {
                this.onlyBackground = new FillBackground(set.getValue(),
                        this.start, this.width, this.height);
            }
        }
        return backgrounds;
    }

    /**
     * Gets block name.
     *
     * @return the block name
     */
    public String getBlockName() {
        return blockName;
    }

    /**
     * @param string - the string setting.
     * @return strok.
     */
    private Stroke getStroke(String string) {
        ColorsParser colorsParser = new ColorsParser();
        Color color = colorsParser.colorFromString(string);
        return new Stroke(color, this.start, this.width, this.height);
    }

    /**
     * @param xpos the xpos
     * @param ypos the ypos
     * @return block
     */
    public Block create(int xpos, int ypos) {
        this.start = new Point(xpos, ypos);
        Block block = new Block(new Rectangle(this.start, this.width, this.height));
        block.setNumbersOfHit(this.hitPoint);
        Map<Integer, Sprite> fillBackground = fill(settings);
        block.setBackground(this.onlyBackground);
        block.setMultipleBackgroung(fillBackground);
        if (settings.containsKey("stroke")) {
            this.stroke = getStroke(settings.get("stroke"));
            block.setStroke(this.stroke);
        }
        return block;
    }

    /**
     * Gets width.
     *
     * @return the width
     */
    public int getWidth() {
        return this.width;
    }


}
