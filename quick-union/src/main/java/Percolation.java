import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

import static edu.princeton.cs.algs4.StdRandom.uniform;

public class Percolation {

    private final int n;
    private final boolean[][] grid;
    private WeightedQuickUnionUF weightedQuickUnionUF;
    private static int numOpenSites; //--------> PRIVATE MAS DEVE METODO PUBLIC ? PARA SER ACEDIDA DO MAIN

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException("n can not be 0 or negative");
        }
        this.grid = new boolean[n][n];
        this.n = n;
        numOpenSites = 0;
    }
    //MAL: By convention, the row and column indices are integers between 1 and n, where (1, 1) is the upper-left site:
    // Throw an IllegalArgumentException if any argument to open(), isOpen(), or isFull() is outside its prescribed range. Throw an IllegalArgumentException in the constructor if n ≤ 0.
    private void validatePosition(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new java.lang.IllegalArgumentException("position is outside the prescribed range");
        }
    }
    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validatePosition(row, col);
        if (!isOpen(row, col)) {
            grid[row][col] = true; //-----> union() - FALTA associar a UM COMPONENTE, OU SEJA USAR QUICKUNION para conectar com elementos À volta
            numOpenSites ++;
        }
        //check if positions (row-1,col), (row,col-1), (row,col+1) and (row+1,col)
        //are opened and connect to the same component if it is the case
        int index = rowColToIndex(row, col);
        if(isOpen(row-1, col)) {
            weightedQuickUnionUF.union(index, rowColToIndex(row-1, col));//-----> LIDAR COM POS OUT OF RANGE --> DÀ EXCEÇÂO
        }
        if(isOpen(row+1, col)) {
            weightedQuickUnionUF.union(index, rowColToIndex(row+1, col));//-----> LIDAR COM POS OUT OF RANGE --> DÀ EXCEÇÂO
        }
        if(isOpen(row, col-1)) {
            weightedQuickUnionUF.union(index, rowColToIndex(row, col-1));//-----> LIDAR COM POS OUT OF RANGE --> DÀ EXCEÇÂO
        }
        if(isOpen(row, col+1)) {
            weightedQuickUnionUF.union(index, rowColToIndex(row, col+1));//-----> LIDAR COM POS OUT OF RANGE --> DÀ EXCEÇÂO
        }
    }
    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validatePosition(row, col);
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validatePosition(row, col);
        /*if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) {
            throw new java.lang.IllegalArgumentException("position is outside the prescribed range");
        }*/ //MAL???
        int index = rowColToIndex(row, col);
        for (int i = 0; i < grid[0].length; i++) {
            //is full if the site is connected to any element of top row
            if (weightedQuickUnionUF.connected(index, rowColToIndex(0,i))) {
                return true;
            }
        }
        return false;
    }

    // returns the number of open sites
    public static int numberOfOpenSites() {
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n ; j++) {
                return (weightedQuickUnionUF.connected(rowColToIndex(n-1,j), rowColToIndex(0,i)));
            }
        }
        return false;
    }

    private static int rowColToIndex(int row, int col) {
        return row * n + col;
    }

    // test client (optional)
    public static void main(String[] args) {

        final int n = 20;
        Percolation p = new Percolation(n);
        int iteration = 0;

        while(!percolates()) {
            //Choose a site uniformly at random
            int siteRow = uniform(n);
            int siteCol = uniform(n);
            //open site
            open(siteRow, siteCol);
        }
        //analyse number open sites to get th
        int th = numberOfOpenSites();


    }

}