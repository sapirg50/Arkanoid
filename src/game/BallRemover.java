package game;

import game.sprite.Ball;
import game.sprite.Block;
import levels.GameLevel;

/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Instantiates a new Ball remover.
     *
     * @param game1        the game 1
     * @param removedBalls the removed balls
     */
    public BallRemover(GameLevel game1, Counter removedBalls) {
        this.game = game1;
        this.remainingBalls = removedBalls;
    }

    /**
     * Gets game.
     *
     * @return the game
     */
    public GameLevel getGame() {
        return game;
    }

    /**
     * Gets remaining balls.
     *
     * @return the remaining balls
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }

    /**
     * Gets remaining balls.
     *
     * @param beingHit the block
     * @param hitter the ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        // Blocks that are hit and reach 0 hit-points should be removed from the game.
        hitter.removeFromGame(this.game);
        //remove this listener from the block that is being removed from the game.
        this.remainingBalls.decrease(1);
    }
}
