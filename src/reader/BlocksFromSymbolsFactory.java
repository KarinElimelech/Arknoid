package reader;

import gameobject.Block;

import java.util.Map;
import java.util.TreeMap;

/**
 * The type Blocks from symbols factory.
 *
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockFactory> blockCreators;

    /**
     * Instantiates a new Blocks from symbols factory.
     */
    BlocksFromSymbolsFactory() {
        this.spacerWidths = new TreeMap<String, Integer>();
        this.blockCreators = new TreeMap<String, BlockFactory>();

    }

    /**
     * Add spacer.
     *
     * @param string  the string
     * @param integer the integer
     */
    public void addSpacer(String string, Integer integer) {
        this.spacerWidths.put(string, integer);
    }

    /**
     * Add block creator.
     *
     * @param string       the string
     * @param blockCreator the block creator
     */
    public void addBlockCreator(String string, BlockFactory blockCreator) {
        this.blockCreators.put(string, blockCreator);
    }

    /**
     * Is space symbol boolean.
     *
     * @param s the s
     * @return the boolean
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }

    /**
     * Is block symbol boolean.
     *
     * @param s the s
     * @return the boolean
     */
    public boolean isBlockSymbol(String s) {
        return this.blockCreators.containsKey(s);
    }

    /**
     * Gets block.
     *
     * @param s    the s
     * @param xpos the xpos
     * @param ypos the ypos
     * @return the block
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return this.blockCreators.get(s).create(xpos, ypos);
    }

    /**
     * Gets width block.
     *
     * @param s the s
     * @return the width block
     */
    public int getWidthBlock(String s) {
        return this.blockCreators.get(s).getWidth();
    }


    /**
     * Gets space width.
     *
     * @param s the s
     * @return the space width
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
}
