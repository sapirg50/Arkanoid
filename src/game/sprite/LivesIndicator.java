package game.sprite;

import biuoop.DrawSurface;
import game.Counter;
import geomtry.Rectangle;
import levels.GameLevel;

import java.awt.Color;

/**
 * The type Lives indicator.
 */
public class LivesIndicator implements Sprite {
    private geomtry.Rectangle rectangle;
    private Counter lives;
    private Color color;

    /**
     * Instantiates a new Lives indicator.
     *
     * @param numOfLives the num of lives
     * @param rect       the rect
     * @param color      the color
     */
    public LivesIndicator(Counter numOfLives, Rectangle rect, Color color) {
        this.lives = numOfLives;
        this.rectangle = rect;
        this.color = color;
    }

    /**
     * Gets lives.
     *
     * @return the lives
     */
    public Counter getLives() {
        return lives;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) rectangle.getUpperLeft().getX(), (int) rectangle.getUpperLeft().getY(),
                (int) rectangle.getWidth(), (int) rectangle.getHeight());
        d.setColor(Color.WHITE);
        int x = (int) (this.rectangle.getUpperLeft().getX() + (this.rectangle.getWidth() / 2 - 30));
        int y = (int) (this.rectangle.getUpperLeft().getY() + (this.rectangle.getHeight() / 2 + 5));
        d.drawText(20, 20, "LIVES: " + this.lives.getValue(), 20);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
