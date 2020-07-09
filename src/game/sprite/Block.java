package game.sprite;

import biuoop.DrawSurface;
import game.Collidable;
import game.HitListener;
import game.HitNotifier;
import game.Velocity;
import geomtry.Line;
import geomtry.Point;
import geomtry.Rectangle;
import levels.GameLevel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * The type game.sprite.Block.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    //members:
    private List<HitListener> hitListeners = new ArrayList<>();
    private Rectangle block;
    private Color stroke;
    private Integer hitPoints;
    private Map<Integer, Color> colorList;
    private Map<Integer, String> imageList;
    private BackgroundColor bgColor;
    private BackgroundImage bgImage;
    private Boolean drawImage, drawColor;

    /**
     * Instantiates a new game.sprite.Block.
     *
     * @param rect      the rect
     * @param stroke    the stroke
     * @param colorList the color list
     * @param imageList the image list
     * @param points    the string
     */
//constructor:
    public Block(Rectangle rect, Color stroke, Map<Integer,
            Color> colorList, Map<Integer, String> imageList, Integer points) {
        this.block = rect;
        this.stroke = stroke;
        this.colorList = colorList;
        this.imageList = imageList;
        this.hitPoints = points;
        this.drawImage = false;
        this.drawColor = false;
        this.bgColor = null;
        this.bgImage = null;
        this.changeBackgroungBlock();
    }

    /**
     * Sets bg color.
     *
     * @param newBgColor the new bg color
     */
    public void setBgColor(BackgroundColor newBgColor) {
        this.bgColor = newBgColor;
    }

    /**
     * Sets bg image.
     *
     * @param newBgImage the new bg image
     */
    public void setBgImage(BackgroundImage newBgImage) {
        this.bgImage = newBgImage;
    }

    /**
     * <p>.
     *
     * @return block.
     */
    public Rectangle getCollisionRectangle() {
        return block;
    }

    /**
     * Gets block.
     *
     * @return the block
     */
    public Rectangle getBlock() {
        return block;
    }

    /**
     * Gets hit points.
     *
     * @return the hit points
     */
    public Integer getHitPoints() {
        return hitPoints;
    }

    /**
     * Change hit points.
     */
    public void changeHitPoints() {
        if (this.getHitPoints() > 0) {
            this.hitPoints -= 1;
        }
    }

    /**
     * Change backgroung block.
     */
    public void changeBackgroungBlock() {
        if (this.colorList != null) {
            if (this.colorList.containsKey(this.hitPoints)) {
                this.drawColor = true;
                this.drawImage = false;
                this.setBgColor(new BackgroundColor(this.colorList.get(this.hitPoints),
                        (int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY(),
                        (int) this.block.getWidth(), (int) this.block.getHeight()));
            }
        }
        if (this.imageList != null) {
            if (this.imageList.containsKey(this.hitPoints)) {
                this.drawColor = false;
                this.drawImage = true;
                this.setBgImage(new BackgroundImage(this.imageList.get(this.hitPoints),
                        (int) this.block.getUpperLeft().getX(), (int) this.block.getUpperLeft().getY()));
            }
        }
    }

    /**
     * Draw on.
     *
     * @param d the surface
     */
    public void drawOn(DrawSurface d) {
        if (this.drawImage) {
            this.bgImage.drawOn(d);
        }
        if (this.drawColor) {
            this.bgColor.drawOn(d);
        }
        if (this.stroke != null) {
            d.setColor(this.stroke);
            d.drawRectangle((int) this.block.getUpperLeft().getX(),
                    (int) this.block.getUpperLeft().getY(), (int) this.block.getWidth(), (int) this.block.getHeight());
        }
    }

    /**
     * timePassed.
     * for now the block doesn't move
     */
    public void timePassed() {
    }

    /**
     * Instantiates a new Add to game.
     *
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * hit.
     * <p>
     * Changes the velocity sign.
     *
     * @param hitter          the ball
     * @param collisionPoint  the collision point.
     * @param currentVelocity the collision point.
     * @return currentVelocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line trajectory = new Line(currentVelocity.applyToPoint(new Point(currentVelocity.getDx()
                + collisionPoint.getX(), currentVelocity.getDy() + collisionPoint.getY())), collisionPoint);
        //Intersection with upper side:
        if (collisionPoint.equals(block.getIntersectionWithUpperSide(trajectory))) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        //Intersection with lower side:
        if (collisionPoint.equals(block.getIntersectionWithLowerSide(trajectory))) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        //Intersection with left side:
        if (collisionPoint.equals(block.getIntersectionWithLeftSide(trajectory))) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        //Intersection with right side:
        if (collisionPoint.equals(block.getIntersectionWithRightSide(trajectory))) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        changeHitPoints();
        this.changeBackgroungBlock();
        this.notifyHit(hitter);
        return currentVelocity;
    }

    /**
     * notify Hit.
     *
     * @param hitter the ball
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Remove from game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
}