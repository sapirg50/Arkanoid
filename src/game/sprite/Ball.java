package game.sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import game.Velocity;
import game.HitNotifier;
import game.HitListener;
import game.GameEnvironment;
import game.CollisionInfo;
import geomtry.Line;
import geomtry.Point;
import levels.GameLevel;

/**
 * game.sprite.Ball class.
 *
 * @author Sapir Graffi ID - 318320488
 */
public class Ball implements Sprite, HitNotifier {
    /**
     * The Hit listeners.
     */
    private List<HitListener> hitListeners = new ArrayList<>();
    private int r;
    private Point center;
    private java.awt.Color color;
    private Velocity v;
    private GameEnvironment gameEnvironment;

    /**
     * Instantiates a new game.sprite.Ball.
     *
     * @param center            the center
     * @param r                 the r
     * @param color             the color
     * @param myGameEnvironment the my game environment
     */
// constructor
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment myGameEnvironment) {
        this.center = center;
        this.color = color;
        this.r = r;
        this.gameEnvironment = myGameEnvironment;
    }

    /**
     * Instantiates a new game.sprite.Ball.
     *
     * @param x               the x
     * @param y               the y
     * @param r               the r
     * @param color           the color
     * @param gameEnvironment the game environment
     */
    public Ball(int x, int y, int r, Color color, GameEnvironment gameEnvironment) {
        this(new Point(x, y), r, color, gameEnvironment);
    }


    /**
     * Gets x.
     *
     * @return the x of the center.
     */
// accessors
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Gets y.
     *
     * @return the y of the center.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Gets size.
     *
     * @return the size (the radius)
     */
    public int getSize() {
        return r;
    }

    /**
     * Gets color.
     *
     * @return the color of the ball.
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * Gets velocity.
     *
     * @return the velocity
     */
    public Velocity getVelocity() {
        return this.v;
    }

    /**
     * Gets center.
     *
     * @return the center
     */
    public Point getCenter() {
        return center;
    }

    /**
     * Draw on.
     * draw the ball on the given DrawSurface
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) this.center.getX(), (int) this.center.getY(), this.r);
    }

    /**
     * Sets velocity.
     *
     * @param velocity the velocity
     */
    public void setVelocity(Velocity velocity) {
        this.v = new Velocity(velocity.getDx(), velocity.getDy());
    }

    /**
     * Sets velocity.
     *
     * @param dx the dx
     * @param dy the dy
     */
    public void setVelocity(double dx, double dy) {
        this.v = new Velocity(dx, dy);
    }

    /**
     * Sets center.
     *
     * @param newCenter the center
     */
    public void setCenter(Point newCenter) {
        this.center = newCenter;
    }

    /**
     * timePassed.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Instantiates a new Add to game.
     *
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * Move one step. Makes sure the center is within borders.
     * if not  - changes the angles.
     */
    public void moveOneStep() {
        if (this.getVelocity() != null) {
            Point p = getVelocity().applyToPoint(this.center);
            Line trajectory = this.calcTrajectory();
            CollisionInfo collision = gameEnvironment.getClosestCollision(trajectory);
            if (collision == null) {
                this.center = this.getVelocity().applyToPoint(this.center);

            } else if (collision.collisionObject().getCollisionRectangle().getUpperLeft().equals(new Point(25, 600))) {
                this.notifyHit((Block) collision.collisionObject());
            } else {
                double newDx = (this.r / Math.sqrt(2)) * Math.signum(this.getVelocity().getDx());
                double newDy = (this.r / Math.sqrt(2)) * Math.signum(this.getVelocity().getDy());
                this.setCenter(new Point(collision.collisionPoint().getX()
                        - newDx, collision.collisionPoint().getY() - newDy));
                setVelocity(collision.collisionObject().hit(this, collision.collisionPoint(), this.getVelocity()));
                //if the ball inside the paddle
                if (collision.collisionObject() instanceof Paddle) {
                    Paddle pd = (Paddle) collision.collisionObject();
                    double pdHeight = pd.getBlockSize().getHeight();
                    if (this.center.getY() >= pd.getBlockSize().getUpperLeft().getY() && (this.center.getX()
                            >= (pd.getBlockSize().getUpperLeft().getX()) && (this.center.getX()
                            <= (pd.getBlockSize().getUpperRight().getX())))) {
                        this.setCenter(new Point(this.center.getX(), this.center.getY() - pdHeight));
                    }
                }
            }
        }
    }

    /**
     * Calc trajectory line.
     *
     * @return the line
     */
    public Line calcTrajectory() {
        return new Line(this.center, this.v.applyToPoint(this.center));
    }

    /**
     * Remove from game.
     *
     * @param game the game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

    /**
     * notifyHit.
     *
     * @param hitter the hitter block.
     */
    private void notifyHit(Block hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(hitter, this);
        }
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