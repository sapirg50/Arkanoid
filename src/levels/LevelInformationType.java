package levels;

import game.LevelInformation;
import game.Velocity;
import game.sprite.BackgroundColor;
import game.sprite.BackgroundImage;
import game.sprite.Block;
import game.sprite.Sprite;

import java.awt.Color;

import java.util.List;

/**
 * The type Level information type.
 */
public class LevelInformationType implements LevelInformation {
    private int speedPaddle, paddleWidth, statX, statY, rowHeight, numOfBlocks;
    private String levelName;
    private BackgroundColor bgColor;
    private BackgroundImage bgImage;
    private String image;
    private Color color;
    private String backgroundType;
    private List<Block> blockList;
    private List<Velocity> velocityList;
    private Boolean[] used;

    /**
     * Instantiates a new Level information type.
     */
    public LevelInformationType() {
        used = new Boolean[10];
        for (int i = 0; i < used.length; i++) {
            this.used[i] = false;
        }
    }

    /**
     * Gets background type.
     *
     * @return the background type
     */
    public String getBackgroundType() {
        return backgroundType;
    }

    /**
     * Gets bg color.
     *
     * @return the bg color
     */
    public BackgroundColor getBgColor() {
        return bgColor;
    }

    /**
     * Gets bg image.
     *
     * @return the bg image
     */
    public BackgroundImage getBgImage() {
        return bgImage;
    }

    /**
     * Gets stat x.
     *
     * @return the stat x
     */
    public int getStatX() {
        return statX;
    }

    /**
     * Gets stat y.
     *
     * @return the stat y
     */
    public int getStatY() {
        return statY;
    }

    /**
     * Gets row height.
     *
     * @return the row height
     */
    public int getRowHeight() {
        return rowHeight;
    }

    /**
     * Sets speed paddle.
     *
     * @param newSpeedPaddle the new speed paddle
     */
    public void setSpeedPaddle(int newSpeedPaddle) {
        this.speedPaddle = newSpeedPaddle;
        this.used[0] = true;
    }

    /**
     * Sets paddle width.
     *
     * @param newPaddleWidth the new paddle width
     */
    public void setPaddleWidth(int newPaddleWidth) {
        this.paddleWidth = newPaddleWidth;
        this.used[1] = true;
    }

    /**
     * Sets stat x.
     *
     * @param newStartX the new stat x
     */
    public void setStatX(int newStartX) {
        this.statX = newStartX;
        this.used[2] = true;
    }

    /**
     * Sets stat y.
     *
     * @param newStatY the stat y
     */
    public void setStatY(int newStatY) {
        this.statY = newStatY;
        this.used[3] = true;
    }

    /**
     * Sets row height.
     *
     * @param newRowHeight the row height
     */
    public void setRowHeight(int newRowHeight) {
        this.rowHeight = newRowHeight;
        this.used[4] = true;
    }

    /**
     * Sets num of blocks.
     *
     * @param newNumOfBlocks the num of blocks
     */
    public void setNumOfBlocks(int newNumOfBlocks) {
        this.numOfBlocks = newNumOfBlocks;
        this.used[5] = true;
    }

    /**
     * Sets level name.
     *
     * @param newLevelName the new level name
     */
    public void setLevelName(String newLevelName) {
        this.levelName = newLevelName;
        this.used[6] = true;
    }

    /**
     * Sets color.
     *
     * @param newColor the new color
     */
    public void setColor(Color newColor) {
        this.color = newColor;
        this.used[7] = true;
        this.bgColor = new BackgroundColor(this.color, 0, 0, 800, 600);
        this.backgroundType = "c";
    }

    /**
     * Sets image.
     *
     * @param newImage the new image
     */
    public void setImage(String newImage) {
        this.image = newImage;
        this.used[7] = true;
        this.bgImage = new BackgroundImage(this.image, 0, 0);
        this.backgroundType = "i";
    }

    /**
     * Sets block list.
     *
     * @param newBlockList the block list
     */
    public void setBlockList(List<Block> newBlockList) {
        this.blockList = newBlockList;
        this.used[8] = true;
    }

    /**
     * Sets velocity list.
     *
     * @param newVelocityList the velocity list
     */
    public void setVelocityList(List<Velocity> newVelocityList) {
        this.velocityList = newVelocityList;
        this.used[9] = true;
    }

    /**
     * number Of Balls int.
     *
     * @return the size of velocityList.
     */
    public int numberOfBalls() {
        return this.velocityList.size();
    }

    /**
     * Is full boolean.
     *
     * @return the boolean
     */
    public Boolean isFull() {
        for (int i = 0; i < this.used.length; i++) {
            if (!this.used[i]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.velocityList;
    }

    @Override
    public int paddleSpeed() {
        return this.speedPaddle;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        if (this.backgroundType.equals("i")) {
            return this.bgImage;
        } else {
            return this.bgColor;
        }
    }

    @Override
    public List<Block> blocks() {
        return this.blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numOfBlocks;
    }
}
