
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final int n;
    private final boolean[][] grid;
    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private int numOpenSites;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n can not be 0 or negative.");
        }

        this.grid = new boolean[n][n];
        this.n = n;
        this.numOpenSites = 0;
        this.weightedQuickUnionUF = new WeightedQuickUnionUF(n * n);
    }

    private boolean isPositionInvalid(int row, int col) {
        return row < 1 || row > n || col < 1 || col > n;
    }

    private void assertPosition(int row, int col) {
        if (isPositionInvalid(row, col)) {
            throw new IllegalArgumentException("Position is outside the prescribed range.");
        }
    }

    public void open(int row, int col) {
        assertPosition(row, col);

        if (!isOpen(row, col)) {
            int index = rowColToIndex(row, col);

            grid[row - 1][col - 1] = true;
            numOpenSites++;

            int[] rows = {row - 1, row + 1, row, row};
            int[] cols = {col, col, col - 1, col + 1};
            
            for (int i = 0; i < rows.length; i++) {
                int neighbourIndex = rowColToIndex(rows[i], cols[i]);

                if (!isPositionInvalid(rows[i], cols[i]) && isOpen(rows[i], cols[i])) {
                    weightedQuickUnionUF.union(index, neighbourIndex);
                }
            }
        }
    }

    public boolean isOpen(int row, int col) {
        assertPosition(row, col);

        return grid[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {
        assertPosition(row, col);

        if (isOpen(row, col)) {
            int index = rowColToIndex(row, col);

            for (int i = 1; i <= n; i++) {
                if (weightedQuickUnionUF.connected(index, rowColToIndex(1, i))) {
                    return true;
                }
            }
        }

        return false;
    }

    public int numberOfOpenSites() {
        return numOpenSites;
    }

    public boolean percolates() {
        for (int i = 1; i <= n; i++) {
            if (isFull(n, i)) {
                return true;
            }
        }

        return false;
    }

    private int rowColToIndex(int row, int col) {
        return (row - 1) * n + (col - 1);
    }

    public static void main(String[] args) {
        final int n = 20;
        Percolation p = new Percolation(n);
        double ths;

        while (!p.percolates()) {
            int siteRow = StdRandom.uniform(n) + 1;
            int siteCol = StdRandom.uniform(n) + 1;

            p.open(siteRow, siteCol);
        }

        ths = (double) p.numberOfOpenSites()/ (n * n);

        System.out.println(ths);
    }

}