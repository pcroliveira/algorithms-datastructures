/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *  
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Comparator;

import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {
        if (that == null) {
            throw new NullPointerException("User must specify x and y coordinates of the point.");
        }

        double dy = that.y - this.y;
        double dx = that.x - this.x;

        if (dy == 0) {
            if (dx == 0) { return Double.NEGATIVE_INFINITY;}

            return 0;
        }
        if (dx == 0) { return Double.POSITIVE_INFINITY; }

        return dy/dx;
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {
        if (that == null) {
            throw new NullPointerException("User must specify x and y coordinates of the point.");
        }
        //pelo argumento receber o tipo Point não é preciso fazer ClassCastException?

        if (this.y < that.y) { return -1;}
        if (this.y == that.y) {
            if (this.x < that.x) { return -1; }
            else if (this.x == that.x) { return 0; }
        }
        return 1;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public Comparator<Point> slopeOrder() {
        return new slopeComparator();
    }

    private class slopeComparator implements Comparator<Point> {

        @Override
        public int compare(Point o1, Point o2) {

            if (o1 == null || o2 == null) {
                throw new NullPointerException("User must specify x and y coordinates of the point.");
            }
            double o1Slope = slopeTo(o1);
            double o2Slope = slopeTo(o2);

            if (o1Slope < o2Slope) { return -1; }
            if (o1Slope == o2Slope) { return 0; }

            return 1;
        }
    }

    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {

        Point point0 = new Point(2,3);
        Point point1 = new Point(4,3);
        Point point2 = new Point(10,2);
        Point point3 = new Point(2, 10);

        System.out.println("Point 0:" + point0.toString());
        System.out.println("Point 1:" + point1.toString());
        System.out.println("Point 2:" + point2.toString());
        System.out.println("Point 3:" + point3.toString());

        //draw point
        point0.draw();
        point1.draw();
        point2.draw();
        point3.draw();

        //compare points by coordinates
        switch (point0.compareTo(point1)) {
            case -1:
                System.out.println("Point 0 is less than point 1");
                break;
            case 0:
                System.out.println("Point 0 is equal to point 1");
                break;
            case 1:
                System.out.println("Point 0 is greater than point 1");
        }
        switch (point1.compareTo(point2)) {
            case -1:
                System.out.println("Point 1 is less than point 2");
                break;
            case 0:
                System.out.println("Point 1 is equal to point 2");
                break;
            case 1:
                System.out.println("Point 1 is greater than point 2");
        }
        switch (point0.compareTo(point2)) {
            case -1:
                System.out.println("Point 0 is less than point 2");
                break;
            case 0:
                System.out.println("Point 0 is equal to point 2");
                break;
            case 1:
                System.out.println("Point 0 is greater than point 2");
        }
        switch (point0.compareTo(point3)) {
            case -1:
                System.out.println("Point 0 is less than point 3");
                break;
            case 0:
                System.out.println("Point 0 is equal to point 3");
                break;
            case 1:
                System.out.println("Point 0 is greater than point 3");
        }
        switch (point1.compareTo(point3)) {
            case -1:
                System.out.println("Point 1 is less than point 3");
                break;
            case 0:
                System.out.println("Point 1 is equal to point 3");
                break;
            case 1:
                System.out.println("Point 1 is greater than point 3");
        }
        switch (point2.compareTo(point3)) {
            case -1:
                System.out.println("Point 2 is less than point 3");
                break;
            case 0:
                System.out.println("Point 2 is equal to point 3");
                break;
            case 1:
                System.out.println("Point 2 is greater than point 3");
        }

        //draw a line from two points
        point0.drawTo(point1);
        point0.drawTo(point2);
        point0.drawTo(point3);

        //slope
        System.out.println("Slope from point 0 to point 1 is:" + point0.slopeTo(point1));
        System.out.println("Slope from point 0 to point 2 is:" + point0.slopeTo(point2));
        System.out.println("Slope from point 0 to point 3 is:" + point0.slopeTo(point3));

        //compare points by their slope to the invoking point
        Comparator<Point> c = point0.slopeOrder();
        int comparison = c.compare(point1, point2);
        if (comparison < 0){
            System.out.println("Point 1 is less than point 2");
        } else if ( comparison == 0 ){
            System.out.println("Point 1 is collinear to point 2");
        } else {
            System.out.println("Point 1 is greater than point 2");
        }
    }
}
