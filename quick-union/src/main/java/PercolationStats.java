import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

    private static final double CONFIDENCE_CONST = 1.96d;

    private final int trials;

    private final double[] results;

    private final double mean;

    private final double stddev;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Size of the grid or number of trials should be greater than 0.");
        }

        this.trials = trials;
        this.results = new double[trials];

        implementPercolation(n);

        this.mean = StdStats.mean(results);
        this.stddev = StdStats.stddev(results);
    }

    private void implementPercolation(int n) {
        for (int i = 0; i < trials; i++) {
            final Percolation percolation = new Percolation(n);

            while (!percolation.percolates()) {
                int siteRow = StdRandom.uniform(n) + 1;
                int siteCol = StdRandom.uniform(n) + 1;

                percolation.open(siteRow, siteCol);
            }

            results[i] = (double) percolation.numberOfOpenSites() / (n * n);
        }
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLo() {
        return mean - (CONFIDENCE_CONST * stddev) / Math.sqrt(trials);
    }

    public double confidenceHi() {
        return mean + (CONFIDENCE_CONST * stddev) / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        PercolationStats pStats = null;

        if (args.length < 2) {
            System.out.println("usage: java PercolationStats n trials");
            return;
        }

        try {
            pStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        } catch (NumberFormatException e) {
            System.out.println("Command line arguments must be integers.");
            return;
        }

        System.out.println("mean = " + pStats.mean());
        System.out.println("stddev = " + pStats.stddev());
        System.out.println("95% confidence interval = [" + pStats.confidenceLo() + "," + pStats.confidenceHi() + "]");
    }
}



