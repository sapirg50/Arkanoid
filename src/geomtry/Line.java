package geomtry; /**
 * geomtry.Line class.
 *
 * @author Sapir Graffi
 * ID - 318320488
 */

import biuoop.DrawSurface;
import java.util.List;

/**
 * The type geomtry.Line.
 */
public class Line {
    private Point start, end;
    private Double m, b;

    /**
     * Instantiates a new geomtry.Line.
     *
     * @param start the start
     * @param end   the end
     */
// constructors
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        //calculates the slope and the intersection point with the y-axis:
        if (this.start.getX() != this.end.getX()) {
            this.m = (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
            this.b = this.start.getY() - m * this.start.getX();
        } else {
            this.m = null;
            this.b = null;
        }
    }

    /**
     * Instantiates a new geomtry.Line.
     *
     * @param x1 the x 1
     * @param y1 the y 1
     * @param x2 the x 2
     * @param y2 the y 2
     */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

    /**
     * Length double.
     *
     * @return the double - the length of the line
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Middle point.
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double x = (start.getX() + end.getX()) / 2;
        double y = (start.getY() + end.getY()) / 2;
        Point midPoint = new Point(x, y);
        return midPoint;
    }

    /**
     * Start point.
     *
     * @return the start point of the line
     */
    public Point start() {
        return start;
    }

    /**
     * End point.
     *
     * @return the end point of the line
     */
    public Point end() {
        return end;
    }

    /**
     * Is intersecting boolean.
     *
     * @param other - other line
     * @return the boolean - true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        return (this.intersectionWith(other) != null);
    }

    /**
     * Intersection with point.
     *
     * @param other the other
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        double x, y;
        if ((this.m == other.m) || ((this.m == null) && (other.m == null))) {
            return null;
        } else if ((this.m == null) && (other.m != null)) {
            x = this.start.getX();
            y = other.m * x + other.b;
        } else if ((other.m == null) && (this.m != null)) {
            x = other.start.getX();
            y = this.m * x + this.b;
        } else {
            x = (this.b - other.b) / (other.m - this.m);
            y = this.m * x + this.b;
        }
        Point intersectingP = new Point(x, y);
        double part1OfThis = intersectingP.distance(this.start);
        double part2OfThis = intersectingP.distance(this.end);
        double part1OfOther = intersectingP.distance(other.start);
        double part2OfOther = intersectingP.distance(other.end);
        double thisDistance = this.end.distance(this.start);
        double otherDistance = other.end.distance(other.start);
        if ((Math.abs(thisDistance - (part1OfThis + part2OfThis)) < 0.1)
                && (Math.abs(otherDistance - (part1OfOther + part2OfOther)) < 0.1)) {
            return intersectingP;
        } else {
            return null;
        }
    }

    /**
     * Closest intersection to start of line point.
     *
     * @param rect the rect
     * @return the point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> listPoints = rect.intersectionPoints(this);
        if (listPoints.isEmpty()) {
            return null;
        } else if (listPoints.size() == 1) {
            return listPoints.get(0);
        } else {
            Point min = listPoints.get(0);
            for (Point i : listPoints) {
                if ((i != null) && (i.distance(this.start) < min.distance(this.start))) {
                    min = i;
                }
            }
            return min;
        }
    }

    /**
     * Equals boolean.
     *
     * @param other the other line
     * @return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        boolean equals1 = this.start.equals(other.start) && this.end.equals(other.end);
        boolean equals2 = this.end.equals(other.start) && this.start.equals(other.end);
        return (equals1 || equals2);
    }

    /**
     * Draw line.
     *
     * @param drawSurface the draw surface
     */
    public void drawLine(DrawSurface drawSurface) {
        drawSurface.drawLine((int) start.getX(), (int) start.getY(), (int) end.getX(), (int) end.getY());
    }
}