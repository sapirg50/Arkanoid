package game.sprite;

import biuoop.DrawSurface;
import levels.GameLevel;

/**
 * The interface game.sprite.Sprite.
 */
public interface Sprite {
    /**
     * Draw on.
     *
     * @param d the d
     */
// draw the sprite to the screen
    void drawOn(DrawSurface d);

    /**
     * Time passed.
     */
// notify the sprite that time has passed
    void timePassed();

    /**
     * Add to game.
     *
     * @param g the g
     */
    void addToGame(GameLevel g);
}