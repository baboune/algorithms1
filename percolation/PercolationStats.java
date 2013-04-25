//import java.util.Arrays;

public class PercolationStats {
    private double[] results;
    private double total_blocks;
    private double mean;
    private double stddev;
    private int TIMES;
    
    public PercolationStats(int N, int T) {
        results = new double[T];
        total_blocks = N * N;
        TIMES = T;
        for(int i = 0; i < T; i++) {
            results[i] = (run_experiment(N));
        }
        //StdOut.println(Arrays.toString(results));
        mean = mean();
        stddev = stddev();
        StdOut.println("mean\t\t\t\t = " + mean());
        StdOut.println("stddev\t\t\t\t = " + stddev());
        StdOut.println("95% confidence interval\t = " + confidenceLo() + ", " + confidenceHi());
    }
    
    private double run_experiment(int N) {
        Percolation p = new Percolation(N);
        int open_count = 0;
        while (!p.percolates()) {
            int i = StdRandom.uniform(1, N + 1);
            int j = StdRandom.uniform(1, N + 1);
            if(!p.isOpen(i, j)) {
                p.open(i, j);
                open_count++;
            }
        }
        double threshold = open_count / total_blocks;
        // return the percolation threshold
        return threshold;
    }
    
    public double mean() {
        return StdStats.mean(results);
    }
    
    public double stddev() {
        return StdStats.stddev(results);
    }
    
    public double confidenceLo() {
        double confidence_lo = mean - ((1.96 * stddev) / Math.sqrt(TIMES));
        return confidence_lo;
    }
    
    public double confidenceHi() {
        double confidence_hi = mean + ((1.96 * stddev) / Math.sqrt(TIMES));
        return confidence_hi;
    }
    
    public static void main(String[] args) {
        //StdOut.println(args[0] + args[1]);
        int N = Integer.parseInt(args[0]);
        int T = Integer.parseInt(args[1]);
        if(N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        //double num = StdRandom.random();
        //StdOut.println(num);
//        int num = StdRandom.uniform(1, 10);
//        StdOut.println(num);
        PercolationStats ps = new PercolationStats(N, T);
    }
}