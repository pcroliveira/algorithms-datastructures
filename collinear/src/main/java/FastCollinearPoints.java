import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {

    private final ArrayList<LineSegment> lineSegments;

    public FastCollinearPoints(Point[] points) {

        if (points == null) {
            throw new IllegalArgumentException("User must specify a vector of points.");
        }
        for (Point p : points) {
            if (p == null) {
                throw new IllegalArgumentException("User must specify coordinates for each point.");
            }
        }

        int nPoints = points.length;
        this.lineSegments = new ArrayList<>();

        Arrays.sort(points);

        for (int i = 0; i < nPoints - 1; i++) {
            int adjacentPoints = 1;
            Point firstPoint = null;

            if (points[i].compareTo(points[i+1]) == 0) {
                throw new IllegalArgumentException("Argument contains repeated points.");
            }

            Comparator<Point> c = points[i].slopeOrder();
            Point[] copyPoints = Arrays.copyOf(points, nPoints);
            Arrays.sort(copyPoints, c);

            for (int j = 1; j < copyPoints.length - 1; j++) {

                if (c.compare(copyPoints[j], copyPoints[j+1]) == 0) {

                    if (adjacentPoints == 1) {
                        firstPoint = copyPoints[j];
                    }
                    adjacentPoints++;

                } else {
                    if (adjacentPoints >= 3 && points[i].compareTo(firstPoint) < 0) {
                        lineSegments.add(new LineSegment(points[i], copyPoints[j]));
                    }
                    adjacentPoints = 1;
                }
            }

            if (adjacentPoints >= 3 && points[i].compareTo(firstPoint) < 0) {
                lineSegments.add(new LineSegment(points[i], copyPoints[copyPoints.length - 1]));
            }
        }

    }

    public int numberOfSegments() {
        return lineSegments.size();
    }

    public LineSegment[] segments() {
        return lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
