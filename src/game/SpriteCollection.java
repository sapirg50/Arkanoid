package game;

import biuoop.DrawSurface;
import game.sprite.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * The type game.sprite.Sprite collection.
 */
public class SpriteCollection {
    private List<Sprite> listOfSprites;

    /**
     * Instantiates a new game.sprite.Sprite collection.
     *
     * @param listOfSprites the list of sprites
     */
    public SpriteCollection(List<Sprite> listOfSprites) {
        this.listOfSprites = listOfSprites;
    }

    /**
     * Instantiates a new game.sprite.Sprite collection.
     */
    public SpriteCollection() {
        this.listOfSprites = new ArrayList<Sprite>();
    }

    /**
     * Add sprite.
     * add the given sprite to the list.
     *
     * @param s the sprite
     */
    public void addSprite(Sprite s) {
        this.listOfSprites.add(s);
    }

    /**
     * remove sprite.
     * remove the given sprite from the list.
     *
     * @param s the sprite
     */
    public void removeSprite(Sprite s) {
        this.listOfSprites.remove(s);
    }

    /**
     * Notify all time passed.
     */
    public void notifyAllTimePassed() {
        List<Sprite> listOfSprites1 = new ArrayList<>(this.listOfSprites);
        for (Sprite i : listOfSprites1) {
            //call timePassed() on all sprites.
            i.timePassed();
        }
    }

    /**
     * Draw all on.
     *
     * @param d the draw surface
     */
    public void drawAllOn(DrawSurface d) {
        List<Sprite> listOfSprites1 = new ArrayList<>(this.listOfSprites);
        for (Sprite i : listOfSprites1) {
            //calls drawOn(d) on all sprites.
            i.drawOn(d);
        }
    }
}