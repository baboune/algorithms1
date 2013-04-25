public class Brute {
    public static void main(String[] args) {

        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);

        // read in the input
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Point p = new Point(x, y);
            points[i] = p; 
            p.draw();
        }
        for (int i = 0; i < N; i++) {
            Point p = points[i];
            for (int j = i + 1; j < N; j++) {
                Point q = points[j];
                for (int k = j + 1; k < N; k++) {
                    Point r = points[k];
                    double pq = p.slopeTo(q);
                    double pr = p.slopeTo(r);
                    
                    if (pq != pr) {
                        continue;
                    }
                    
                    for (int l = k + 1; l < N; l++) {
                        Point s = points[l];
                        double ps = p.slopeTo(s);
                        
                        if (pq != ps) {
                            continue;
                        }
                        p.drawTo(s);
                        p.drawTo(r);
                        p.drawTo(q);
                        StdOut.printf("%s -> %s -> %s -> %s\n", p, q, r, s);
                    }
                }
            }
        }
        // display to screen all at once
        StdDraw.show(0);
    }
}