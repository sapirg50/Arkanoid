package construction;

import game.sprite.Block;

/**
 * The interface Block creator.
 */
public interface BlockCreator {
    /**
     * Create block.
     * Create a block at the specified location.
     *
     * @param xpos the x position
     * @param ypos the y posision
     * @return the block
     */
    Block create(int xpos, int ypos);

    /**
     * Gets width.
     *
     * @return the width
     */
    int getWidth();
}