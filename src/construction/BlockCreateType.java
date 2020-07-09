package construction;

import game.sprite.Block;
import geomtry.Rectangle;

import java.awt.Color;
import java.util.Map;


/**
 * The type Block create type.
 */
public class BlockCreateType implements BlockCreator {
    private int height, width, hitPoints;
    private Color stroke;
    private Map<Integer, Color> colorList;
    private Map<Integer, String> imageList;
    private Boolean[] used;

    /**
     * Instantiates a new Block create type.
     */
    public BlockCreateType() {
        this.used = new Boolean[4];
        for (int i = 0; i < used.length; i++) {
            this.used[i] = false;
        }
    }

    @Override
    public int getWidth() {
        return width;
    }

    /**
     * Sets height.
     *
     * @param newHeight the new height
     */
    public void setHeight(int newHeight) {
        this.height = newHeight;
        this.used[0] = true;
    }

    /**
     * Sets width.
     *
     * @param newWidth the new width
     */
    public void setWidth(int newWidth) {
        this.width = newWidth;
        this.used[1] = true;
    }

    /**
     * Sets hit points.
     *
     * @param hitPoints1 the hit points
     */
    public void setHitPoints(int hitPoints1) {
        this.hitPoints = hitPoints1;
        this.used[2] = true;
    }

    /**
     * Sets stroke.
     *
     * @param newStroke the new stroke
     */
    public void setStroke(Color newStroke) {
        this.stroke = newStroke;
    }

    /**
     * Sets color list.
     *
     * @param listColor the list color
     */
    public void setColorList(Map<Integer, Color> listColor) {
        this.colorList = listColor;
        this.used[3] = true;
    }

    /**
     * Sets image list.
     *
     * @param listImage the list image
     */
    public void setImageList(Map<Integer, String> listImage) {
        this.imageList = listImage;
        this.used[3] = true;
    }

    /**
     * Is full boolean.
     *
     * @return the boolean
     */
    public boolean isFull() {
        for (int i = 0; i < this.used.length; i++) {
            if (!this.used[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Block create(int xpos, int ypos) {
        Rectangle rect = new Rectangle((double) xpos, (double) ypos, (double) this.width, (double) this.height);
        return new Block(rect, this.stroke, this.colorList, this.imageList, this.hitPoints);
    }
}
