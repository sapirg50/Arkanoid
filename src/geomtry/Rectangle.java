package geomtry;

import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

/**
 * The type geomtry.Rectangle.
 */
public class Rectangle {
    //members:
    private Point upperLeft;
    private double width, height;
    private Color color;

    /**
     * Instantiates a new geomtry.Rectangle.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     */
//constructor:
    // Create a new rectangle with location and width/height.
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Instantiates a new Rectangle.
     *
     * @param x      the x
     * @param y      the y
     * @param width  the width
     * @param height the height
     */
    public Rectangle(double x, double y, double width, double height) {
        this(new Point(x, y), width, height);
    }

    /**
     * geomtry.Rectangle points point.
     *
     * @param p       the p
     * @param width1  the width
     * @param height1 the height
     * @return the point
     */
    public Point rectanglePoints(Point p, double width1, double height1) {
        double x = p.getX() + width1;
        double y = p.getY() + height1;
        return new Point(x, y);
    }

    /**
     * Intersection points java . util . list.
     *
     * @param line the line
     * @return the java . util . list
     */
// Return a (possibly empty) List of intersection points with the specified line.
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> listPoints = new ArrayList<Point>();
        if (this.getIntersectionWithUpperSide(line) != null) {
            listPoints.add(getIntersectionWithUpperSide(line));
        }
        if (this.getIntersectionWithLowerSide(line) != null) {
            listPoints.add(getIntersectionWithLowerSide(line));
        }
        if (this.getIntersectionWithRightSide(line) != null) {
            listPoints.add(getIntersectionWithRightSide(line));
        }
        if (this.getIntersectionWithLeftSide(line) != null) {
            listPoints.add(getIntersectionWithLeftSide(line));
        }
        return listPoints;
    }

    /**
     * Closest intersection point point.
     *
     * @param line the line
     * @return the point
     */
    public Point closestIntersectionPoint(Line line) {
        return line.closestIntersectionToStartOfLine(this);
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Gets width.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Gets height.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Gets upper left.
     *
     * @return the upper left of the rectangle.
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * Gets upper right.
     *
     * @return the upper right of the rectangle.
     */
    public Point getUpperRight() {
        return rectanglePoints(this.getUpperLeft(), getWidth(), 0);
    }

    /**
     * Gets lower right.
     *
     * @return the lower right of the rectangle.
     */
    public Point getLowerRight() {
        return rectanglePoints(this.getUpperLeft(), this.getWidth(), this.getHeight());
    }

    /**
     * Gets lower left.
     *
     * @return the lower left of the rectangle.
     */
    public Point getLowerLeft() {
        return rectanglePoints(this.getUpperLeft(), 0, this.getHeight());
    }

    /**
     * Get intersection with upper side point.
     *
     * @param line the line
     * @return the point
     */
    public Point getIntersectionWithUpperSide(Line line) {
        return line.intersectionWith(new Line(this.getUpperLeft(), this.getUpperRight()));
    }

    /**
     * Get intersection with lower side point.
     *
     * @param line the line
     * @return the point
     */
    public Point getIntersectionWithLowerSide(Line line) {
        return line.intersectionWith(new Line(this.getLowerLeft(), this.getLowerRight()));
    }

    /**
     * Get intersection with left side point.
     *
     * @param line the line
     * @return the point
     */
    public Point getIntersectionWithLeftSide(Line line) {
        return line.intersectionWith(new Line(this.getUpperLeft(), this.getLowerLeft()));
    }

    /**
     * Get intersection with right side point.
     *
     * @param line the line
     * @return the point
     */
    public Point getIntersectionWithRightSide(Line line) {
        return line.intersectionWith(new Line(this.getUpperRight(), this.getLowerRight()));
    }

    /**
     * Sets color.
     *
     * @param color1 the color
     */
    public void setColor(Color color1) {
        this.color = color1;
    }

    /**
     * Sets upper left.
     *
     * @param newUpperLeft the new upper left
     */
    public void setUpperLeft(Point newUpperLeft) {
        this.upperLeft = newUpperLeft;
    }

    /**
     * Sets upper left x.
     *
     * @param dx the dx
     */
    public void setUpperLeftX(double dx) {
        setUpperLeft(new Point(getUpperLeft().getX() + dx, getUpperLeft().getY()));
    }
}
