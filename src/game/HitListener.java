package game;

import game.sprite.Ball;
import game.sprite.Block;

/**
 * The interface Hit listener.
 */
public interface HitListener {
    /**
     * Hit event.
     *
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the game. Ball that's doing the hitting.
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    void hitEvent(Block beingHit, Ball hitter);
}