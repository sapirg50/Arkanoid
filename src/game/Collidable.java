package game;

import game.sprite.Ball;
import geomtry.Point;
import geomtry.Rectangle;

/**
 * The interface game.Collidable.
 */
public interface Collidable {
    /**
     * Gets collision rectangle.
     *
     * @return the collision rectangle
     */
// Return the "collision shape" of the object.
    Rectangle getCollisionRectangle();

    /**
     * Hit velocity.
     *
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity
     * @param hitter the ball
     * @return the velocity - the new velocity expected after the hit (based on the force the object inflicted on us).
     */
    //Notify the object that we collided with it at collisionPoint with a given velocity.
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}