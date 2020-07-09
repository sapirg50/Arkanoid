package game.sprite;

import biuoop.DrawSurface;
import game.Counter;
import geomtry.Rectangle;
import levels.GameLevel;

import java.awt.Color;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    //members:
    private Rectangle rect;
    private Counter points;
    private Color color;

    /**
     * Instantiates a new Score indicator.
     *
     * @param points the points
     * @param rect   the rect
     * @param color  the color
     */
//constructor:
    public ScoreIndicator(Counter points, Rectangle rect, Color color) {
        this.rect = rect;
        this.points = points;
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                (int) rect.getWidth(), (int) rect.getHeight());
        d.setColor(Color.WHITE);
        int x = (int) this.rect.getUpperLeft().getX() + 50;
        int y = (int) (this.rect.getUpperLeft().getY() + (this.rect.getHeight() / 2 + 5));
        d.drawText(x, y, "SCORE: " + this.points.getValue(), 20);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
