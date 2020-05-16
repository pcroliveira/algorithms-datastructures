import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class BruteCollinearPoints {

    private final ArrayList<LineSegment> lineSegments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {

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

        for (int i = 0; i < nPoints; i++) {
            for (int j = i+1; j < nPoints; j++) {
                for (int k = j+1; k < nPoints; k++) {
                    for (int l = k+1; l < nPoints; l++) {

                        int pointsIJ = points[i].compareTo(points[j]);
                        int pointsIK = points[i].compareTo(points[k]);
                        int pointsIL = points[i].compareTo(points[l]);
                        int pointsJK = points[j].compareTo(points[k]);
                        int pointsJL = points[j].compareTo(points[l]);
                        int pointsKL = points[k].compareTo(points[l]);

                        if (pointsIJ == 0 || pointsIK == 0 || pointsIL == 0 ||
                                pointsJK == 0 || pointsJL == 0 || pointsKL == 0) {
                            throw new IllegalArgumentException("Argument contains repeated points.");
                        }

                        Point minPoint = points[i];
                        Point maxPoint = points[i];

                        if (pointsIJ > 0 && pointsJK < 0 && pointsJL < 0) { minPoint = points[j]; }
                        else if (pointsIK > 0 && pointsJK > 0 && pointsKL < 0) { minPoint = points[k]; }
                        else if (pointsIL > 0 && pointsJL > 0 && pointsKL > 0) { minPoint = points[l]; }

                        if (pointsIJ < 0 && pointsJK > 0 && pointsJL > 0) { maxPoint = points[j]; }
                        else if (pointsIK < 0 && pointsJK < 0 && pointsKL > 0) { maxPoint = points[k]; }
                        else if (pointsIL < 0 && pointsJL < 0 && pointsKL < 0) { maxPoint = points[l]; }

                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) &&
                                points[i].slopeTo(points[j]) == points[i].slopeTo(points[l])) {
                            lineSegments.add(new LineSegment(minPoint, maxPoint));
                        }
                    }
                }
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}