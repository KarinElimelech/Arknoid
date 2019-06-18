package reader;

import gameobject.Block;

/**
 * The interface Block creator.
 * @author : Karin Elimelech
 * @version : 17/06/18
 */
public interface BlockCreator {
    /**
     * Create block.
     *
     * @param xpos the xpos
     * @param ypos the ypos
     * @return the block
     */
// Create a block at the specified location.
    Block create(int xpos, int ypos);
}
