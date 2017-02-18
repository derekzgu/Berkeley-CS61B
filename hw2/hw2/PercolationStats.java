package hw2;

import edu.princeton.cs.introcs.StdStats;

import static edu.princeton.cs.introcs.StdRandom.uniform;
import static java.lang.Math.sqrt;

public class PercolationStats {
    /* Perform T independent experiments on an N-by-N grid. */
    private int N, T;
    private double mean, stddev;
    private double confidenceLow, confidenceHigh;

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("arguments are not positive integers");
        }
        double[] sample = new double[T];
        /* Do T experiments. */
        for (int i = 0; i < T; ++i) {
            Percolation percolationExperiment = new Percolation(N);
            do {
                percolationExperiment.open(uniform(N), uniform(N));
            } while (!percolationExperiment.percolates());
            sample[i] = percolationExperiment.numberOfOpenSites() / (double) (N * N);
        }
        mean = StdStats.mean(sample);
        stddev = StdStats.stddev(sample);
        confidenceLow = mean - 1.96 * stddev / sqrt(T);
        confidenceHigh = mean + 1.96 * stddev / sqrt(T);
    }

    public double mean() {
        return mean;
    }

    public double stddev() {
        return stddev;
    }

    public double confidenceLow() {
        return confidenceLow;
    }

    public double confidenceHigh() {
        return confidenceHigh;
    }

    /* Test */
    public static void main(String[] args) {
        PercolationStats percolationStats = new PercolationStats(20, 100000);
        System.out.println("Mean: " + percolationStats.mean());
        System.out.println("Standard Deviation: " + percolationStats.stddev());
        System.out.println("Confidence Interval: [" + percolationStats.confidenceLow() + ", " +
                percolationStats.confidenceHigh() + "]");

    }
}                       
