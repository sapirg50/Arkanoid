package game.sprite;

import biuoop.DrawSurface;
import geomtry.Rectangle;
import levels.GameLevel;

import java.awt.Color;

/**
 * The type Level name indicator.
 */
public class LevelNameIndicator implements Sprite {
    private geomtry.Rectangle rectangle;
    private String levelName;
    private Color color;


    /**
     * Instantiates a new Level name indicator.
     *
     * @param name  the name
     * @param rect  the rect
     * @param color the color
     */
    public LevelNameIndicator(String name, Rectangle rect, Color color) {
        this.color = color;
        this.levelName = name;
        this.rectangle = rect;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rectangle.getUpperLeft().getX(), (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        d.setColor(Color.WHITE);
        int x = (int) (this.rectangle.getUpperLeft().getX());
        int y = (int) (this.rectangle.getUpperLeft().getY() + (this.rectangle.getHeight() / 2 + 5));
        d.drawText(x, y, "Level Name: " + this.levelName, 20);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
