package geomtry;

/**
 * geomtry.Point class.
 *
 * @author Sapir Graffi
 * ID - 318320488
 */
public class Point {
    private double x;
    private double y;

    /**
     * Instantiates a new geomtry.Point.
     *
     * @param x the x
     * @param y the y
     */
// constructor
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance double.
     *
     * @param other the other point
     * @return the double- the distance of this point to the other point
     */
    public double distance(Point other) {
        return Math.sqrt(((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y)));
    }

    /**
     * Equals boolean.
     *
     * @param other the other point
     * @return the boolean - true if the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return (Math.abs(this.x - other.x) <= 0.01) && (Math.abs(this.y - other.y) <= 0.01);
    }

    /**
     * Gets x.
     *
     * @return the x value of this point.
     */
    public double getX() {
        return x;
    }

    /**
     * Gets y.
     *
     * @return the y value of this point
     */
    public double getY() {
        return y;
    }
}