package game.sprite;

import biuoop.DrawSurface;
import levels.GameLevel;

import java.awt.Color;

/**
 * The type Background color.
 */
public class BackgroundColor implements Sprite {
    private Color color;
    private int x, y, width, height;

    /**
     * Instantiates a new Background color.
     *
     * @param color  the color
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     */
    public BackgroundColor(Color color, int x, int y, int width, int height) {
        this.color = color;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(this.x, this.y, this.width, this.height);
        //  d.fillRectangle(this.x, this.y, this.width, this.height);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
