package game.sprite;

import biuoop.DrawSurface;
import game.Collidable;
import game.Velocity;
import geomtry.Line;
import geomtry.Point;
import geomtry.Rectangle;
import levels.GameLevel;

import java.awt.Color;

/**
 * The type Border.
 */
public class Border implements Sprite, Collidable {
    private Rectangle rectBorder;
    private BackgroundColor background;

    /**
     * Instantiates a new Border.
     *
     * @param rect  the rect
     * @param color the color
     */
    public Border(Rectangle rect, Color color) {
        this.rectBorder = rect;
        this.background = new BackgroundColor(color, (int) this.rectBorder.getUpperLeft().getX(),
                (int) this.rectBorder.getUpperLeft().getY(), (int) this.rectBorder.getWidth(),
                (int) this.rectBorder.getHeight());
    }

    @Override
    public void drawOn(DrawSurface d) {
        this.background.drawOn(d);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    @Override
    public Rectangle getCollisionRectangle() {
        return this.rectBorder;
    }

    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line trajectory = new Line(currentVelocity.applyToPoint(new Point(currentVelocity.getDx()
                + collisionPoint.getX(), currentVelocity.getDy() + collisionPoint.getY())), collisionPoint);
        double lengthOfRegion = this.rectBorder.getWidth() / 5;
        double x = collisionPoint.getX() - this.rectBorder.getUpperLeft().getX();
        double speed = Math.hypot(currentVelocity.getDx(), currentVelocity.getDy());
        //Intersection with upper side:
//        if (collisionPoint.equals(rectBorder.getIntersectionWithUpperSide(trajectory))) {
//            //currentVelocity.setDy(-currentVelocity.getDy());
//        }
        //Intersection with lower side:
        if (collisionPoint.equals(rectBorder.getIntersectionWithLowerSide(trajectory))) {
            currentVelocity.setDy(-currentVelocity.getDy());
        }
        //Intersection with left side:
        if (collisionPoint.equals(rectBorder.getIntersectionWithLeftSide(trajectory))) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        //Intersection with right side:
        if (collisionPoint.equals(rectBorder.getIntersectionWithRightSide(trajectory))) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        return currentVelocity;
    }
}
