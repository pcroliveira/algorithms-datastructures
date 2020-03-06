import edu.princeton.cs.algs4.StdRandom;


public class Main {


    private static class RowCol {

        private int row;

        private int col;

        public RowCol(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public String toString() {
            return "(" + row + ", " + col + ")";
        }
    }

    public static class Random implements Percolation.RandomNumberGenerator {
        @Override
        public float getRandomFloat(float minVal, float maxValue) {
            return StdRandom.uniform(0);
        }
    }

    public static void main(String[] args) {
        Percolation percolation = new Percolation(10, new Random());



    }

//    public static void main(String[] args) {
//
//        /*final int n = 20;
//        Percolation p = new Percolation(n);
//        int iteration = 0;
//
//        while(!p.percolates()) {
//            //Choose a site uniformly at random
//            int siteRow = StdRandom.uniform(n);
//            int siteCol = StdRandom.uniform(n);
//            System.out.println(siteRow + " " + siteCol);
//
//            //open site
//            p.open(siteRow, siteCol);
//        }
//        //analyse number open sites to get th
//        int result = p.numberOfOpenSites();
//
//        System.out.println(result);*/
////        int n = 2;
////        int T = 10000;
////        PercolationStats pStats;
////
////        //takes two command-line arguments n and T, performs T independent computational experiments
////        //pStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
////        pStats = new PercolationStats(n, T);
////
////        //prints the sample mean, sample standard deviation, and the 95% confidence interval for the percolation ths
////        System.out.println("mean = " + pStats.mean());
////        System.out.println("stddev = " + pStats.stddev());
////        System.out.println("95% confidence interval = [" + pStats.confidenceLo() + "," + pStats.confidenceHi() + "]");
//
//
//        RowCol rowCol = new RowCol(1,1);
//
//        RowCol[] rowColArray = new RowCol[] {
//                rowCol,
//                rowCol,
//                rowCol,
//                rowCol
//        };
//
//        rowColArray[0].col = 2;
//        System.out.println(rowColArray[0]);
//        System.out.println(rowColArray[1]);
//
//    }
}
