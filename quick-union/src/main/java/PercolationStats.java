import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {

    private final int trials;

    private final double[] results;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException("Size of the grid or number of trials should be greater than 0.");
        }

        this.trials = trials;
        this.results = new double[trials];

        implementPercolation(n);
    }

    private void implementPercolation(int n) {
        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);

            while (!percolation.percolates()) {
                int siteRow = StdRandom.uniform(n) + 1;
                int siteCol = StdRandom.uniform(n) + 1;

                percolation.open(siteRow, siteCol);
            }

            results[i] = (double) percolation.numberOfOpenSites() / (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(results);
    }

    public double stddev() {
        return StdStats.stddev(results);
    }

    public double confidenceLo() {
        return mean() - (1.96 * stddev()) / Math.sqrt(trials);
    }

    public double confidenceHi() {
        return mean() + (1.96 * stddev()) / Math.sqrt(trials);
    }

    public static void main(String[] args) {
        PercolationStats pStats = null;

        if (args.length < 2) {
            System.out.println("usage: java PercolationStats n trials");
            System.exit(1);
        }

        try {
            pStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        } catch (NumberFormatException e) {
            System.err.println("Command line arguments must be integers.");
            System.exit(1);
        }

        System.out.println("mean = " + pStats.mean());
        System.out.println("stddev = " + pStats.stddev());
        System.out.println("95% confidence interval = [" + pStats.confidenceLo() + "," + pStats.confidenceHi() + "]");
    }
}



