package reader;

import java.awt.Color;
import java.util.Map;
import java.util.TreeMap;

/**
 * The type Colors parser.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class ColorsParser {
    private static final String REGEX_RGB = "color\\(RGB\\(([0-9]+,[0-9]+,[0-9]+)\\)\\)";

    /**
     * Color from string java . awt . color.
     *
     * @param colorString the color string
     * @return the java . awt . color
     */
    public java.awt.Color colorFromString(String colorString) {
        String subs = colorString.substring(colorString.lastIndexOf("(") + 1,
                colorString.indexOf(")"));
        if (colorString.matches(REGEX_RGB)) {
            String[] rgb = subs.split(",");
            Color color = new Color(Integer.parseInt(rgb[0]),
                    Integer.parseInt(rgb[1]),
                    Integer.parseInt(rgb[2]));
            return color;
        } else {
            Map<String, Color> colorMap = new TreeMap<String, Color>();
            colorMap.put("yellow", Color.yellow);
            colorMap.put("red", Color.red);
            colorMap.put("black", Color.black);
            colorMap.put("blue", Color.blue);
            colorMap.put("cyan", Color.cyan);
            colorMap.put("gray", Color.gray);
            colorMap.put("lightGray", Color.lightGray);
            colorMap.put("green", Color.green);
            colorMap.put("orange", Color.orange);
            colorMap.put("pink", Color.pink);
            colorMap.put("white", Color.white);
            return colorMap.get(subs);
        }
    }
}
