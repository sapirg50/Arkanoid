package game.sprite;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Collidable;
import game.Velocity;
import geomtry.Line;
import geomtry.Point;
import geomtry.Rectangle;
import levels.GameLevel;

import java.awt.Color;

/**
 * The type game.sprite.Paddle.
 */
public class Paddle implements Collidable, Sprite {
    //members:
    private Rectangle paddle;
    private int speedPaddle;
    private biuoop.KeyboardSensor keyboard;

    /**
     * Instantiates a new game.sprite.Paddle.
     *
     * @param paddle      the rect
     * @param keyboard    the keyboard
     * @param speedPaddle the speed of the paddle
     */
    public Paddle(Rectangle paddle, KeyboardSensor keyboard, int speedPaddle) {
        this.paddle = paddle;
        this.speedPaddle = speedPaddle;
        this.keyboard = keyboard;
    }

    /**
     * Move left.
     */
    public void moveLeft() {
        if ((keyboard.isPressed(KeyboardSensor.LEFT_KEY)) && (this.paddle.getUpperLeft().getX() > 30)) {
            this.paddle.setUpperLeftX(-this.speedPaddle);
        }
    }

    /**
     * Move right.
     */
    public void moveRight() {
        if ((keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) && (this.paddle.getUpperRight().getX() < 800 - 30)) {
            this.paddle.setUpperLeftX(this.speedPaddle);
        }
    }

    /**
     * timePassed.
     */
    public void timePassed() {
        moveRight();
        moveLeft();
    }

    /**
     * Draw on.
     *
     * @param d the drawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.YELLOW);
        d.fillRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.paddle.getUpperLeft().getX(), (int) this.paddle.getUpperLeft().getY(),
                (int) this.paddle.getWidth(), (int) this.paddle.getHeight());
    }

    /**
     * <p>.
     *
     * @return paddle.
     */
    public Rectangle getCollisionRectangle() {
        return paddle;
    }

    /**
     * hit.
     * <p>
     * Changes the velocity sign.
     *
     * @param collisionPoint  the collision point.
     * @param currentVelocity the collision point.
     * @param hitter          the ball.
     * @return currentVelocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line trajectory = new Line(currentVelocity.applyToPoint(new Point(currentVelocity.getDx()
                + collisionPoint.getX(), currentVelocity.getDy() + collisionPoint.getY())), collisionPoint);
        double lengthOfRegion = this.paddle.getWidth() / 5;
        double x = collisionPoint.getX() - this.paddle.getUpperLeft().getX();
        double speed = Math.hypot(currentVelocity.getDx(), currentVelocity.getDy());
        if (collisionPoint.equals(paddle.getIntersectionWithUpperSide(trajectory))) {
            //if the region isn't 3:
            if ((int) (x / lengthOfRegion) + 1 != 3) {
                currentVelocity = currentVelocity.fromAngleAndSpeed(((int) (x / lengthOfRegion) * 30) - 60, speed);
            } else {
                // if the region is 3:
                currentVelocity.setDy(-currentVelocity.getDy());
            }
        }
        if (collisionPoint.equals(paddle.getIntersectionWithLeftSide(trajectory))) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        if (collisionPoint.equals(paddle.getIntersectionWithRightSide(trajectory))) {
            currentVelocity.setDx(-currentVelocity.getDx());
        }
        return currentVelocity;
    }

    // Add this paddle to the game:

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
     * Gets paddle size.
     *
     * @return the block size
     */
    public Rectangle getBlockSize() {
        return paddle;
    }
}
