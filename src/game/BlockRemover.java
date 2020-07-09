package game;

import game.sprite.Ball;
import game.sprite.Block;
import levels.GameLevel;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Block remover.
 */
// a game.BlockRemover is in charge of removing blocks from the game, as well as keeping count
// of the number of blocks that remain.
public class BlockRemover implements HitListener, HitNotifier {
    private GameLevel game;
    private Counter remainingBlocks;
    private List<HitListener> hitListeners = new ArrayList<>();

    /**
     * Instantiates a new Block remover.
     *
     * @param game1         the game 1
     * @param removedBlocks the removed blocks
     */
    public BlockRemover(GameLevel game1, Counter removedBlocks) {
        this.game = game1;
        this.remainingBlocks = removedBlocks;
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
     * Gets remaining blocks.
     *
     * @return the remaining blocks
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    /**
     * hit Event.
     *
     * @param hitter the ball
     * @param beingHit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            //Blocks that are hit and reach 0 hit-points should be removed from the game.
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.game);
            //remove this listener from the block that is being removed from the game.
            this.remainingBlocks.decrease(1);
        }
    }

    @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }
}