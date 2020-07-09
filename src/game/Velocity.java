package game;

import geomtry.Point;

/**
 * game.sprite.Ball class.
 *
 * @author Sapir Graffi ID - 318320488
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Instantiates a new game.Velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
// constructor
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Sets dx.
     *
     * @param deltaX the delta x
     */
    public void setDx(double deltaX) {
        this.dx = deltaX;
    }

    /**
     * Sets dy.
     *
     * @param deltaY the delta y
     */
    public void setDy(double deltaY) {
        this.dy = deltaY;
    }

    /**
     * Gets dx.
     *
     * @return the dx
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Gets dy.
     *
     * @return the dy
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * From angle and speed velocity.
     *
     * @param angle the angle
     * @param speed the speed
     * @return the velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = speed * Math.sin(Math.toRadians(angle));
        double dy = -speed * Math.cos(Math.toRadians(angle));
        return new Velocity(dx, dy);
    }

    /**
     * Apply to point point.
     *
     * @param p the point
     * @return the point with new position.
     */
    public Point applyToPoint(Point p) {
        // Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}