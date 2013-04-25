/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;
import java.util.Arrays;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER;       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
        SLOPE_ORDER = new SlopeOrder(this);
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        if (this.y - that.y == 0 && this.x - that.x == 0) {
            return Double.NEGATIVE_INFINITY;
        } else if (this.x - that.x == 0) {
            return Double.POSITIVE_INFINITY;
        } else {
            return (((double)this.y - (double)that.y) / ((double)this.x - (double)that.x));
        }
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if (y == that.y && x == that.x) {
            return 0;
        }
        if (y < that.y || y == that.y && x < that.x) {
            return -1;
        }
        return 1;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
    
    private class SlopeOrder implements Comparator<Point> {
        private Point base;
        
        private SlopeOrder(Point base) {
            this.base = base;
        }
        
        public int compare(Point v, Point w) {
            double s1 = base.slopeTo(v);
            double s2 = base.slopeTo(w);
            
            if (s1 < s2) {
                return -1;
            }
            if (s1 > s2) {
                return 1;
            }
            return v.compareTo(w);
        }
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        Point p1 = new Point(1, 1);
        Point p2 = new Point(2, 2);
        Point p3 = new Point(2, 1);
        Point p4 = new Point(3, 1);
        Point p5 = new Point(2, 5);
        Point p6 = new Point(1, 1);
        Point p7 = new Point(3, 200);
//        ArrayList<String> points = new ArrayList<String>();
//        points.add(p1);
//        points.add(p2);
//        points.add(p3);
        Point[] points = new Point[4];
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        //Arrays.sort(points);
        StdOut.println(p1.slopeTo(p2)); // 1.0
        StdOut.println(p1.slopeTo(p3)); // 0.0
        StdOut.println(p2.slopeTo(p3)); // Infinity
        StdOut.println(p1.slopeTo(p1)); // -Infinity
        StdOut.println(p2.slopeTo(p4)); // -1.0
        StdOut.println(p1.slopeTo(p5)); // 4
        StdOut.println(p5.slopeTo(p1)); // 4
        StdOut.println(p6.slopeTo(p7)); // ???
    }
}