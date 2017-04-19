package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
/**
 * @author Wending Peng
 */
public class Percolation {

    private class Site {
        boolean isOpen;
        int index;

        public Site() {
            isOpen = false;
            index = 0;
        }
    }

    private int virtualTop;
    private int virtualBottom;

    private WeightedQuickUnionUF weightedQuickUnionUF;
    private WeightedQuickUnionUF weightedQuickUnionUFWithoutBOTTOM;

    private Site[][] grid;
    private int side;
    private int numberOfOpenSites;

    private final int[] dx = {-1, 0, 0, 1};
    private final int[] dy = {0, -1, 1, 0};

    /* Change item of the 2D array to an index. */
    private int xyToIndex(int row, int col) {
        return row * side + col;
    }

    /* Creates N by N grid, with all sites initially blocked. */
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("number of grid in each side of square is not positive");
        }
        weightedQuickUnionUF = new WeightedQuickUnionUF(N * N + 2);
        weightedQuickUnionUFWithoutBOTTOM = new WeightedQuickUnionUF(N * N + 1);
        grid = new Site[N][N];
        side = N;
        virtualTop = N * N;
        virtualBottom = N * N + 1;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                grid[i][j] = new Site();
                grid[i][j].index = xyToIndex(i, j);
            }
        }
        numberOfOpenSites = 0;
    }

    public void open(int row, int col) {
        if (row < 0 || col < 0 || row >= side || col >= side) {
            throw new IllegalArgumentException("row and column is not in the range of [0, side - 1]");
        }
        if (!isOpen(row, col)) {
            ++numberOfOpenSites;
            grid[row][col].isOpen = true;
        }
        if (row == 0) {
            weightedQuickUnionUF.union(virtualTop, grid[row][col].index);
            weightedQuickUnionUFWithoutBOTTOM.union(virtualTop, grid[row][col].index);
        } else if (row == side - 1) {
            weightedQuickUnionUF.union(virtualBottom, grid[row][col].index);
        }
        for (int i = 0; i < 4; ++i) {
            int neighborX = dx[i] + row;
            int neighborY = dy[i] + col;
            if (0 <= neighborX && neighborX < side && 0 <= neighborY && neighborY < side
                    && grid[neighborX][neighborY].isOpen) {
                /* This neighbor site is valid and open. */
                weightedQuickUnionUF.union(grid[neighborX][neighborY].index, grid[row][col].index);
                weightedQuickUnionUFWithoutBOTTOM.union(grid[neighborX][neighborY].index, grid[row][col].index);
            }
        }
    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0 || row >= side || col >= side) {
            throw new IllegalArgumentException("row and column is not in the range of [0, side - 1]");
        }
        return grid[row][col].isOpen;
    }


    public int numberOfOpenSites() {
        return numberOfOpenSites;
    }

    public boolean isFull(int row, int col) {
        if (row < 0 || col < 0 || row >= side || col >= side) {
            throw new IllegalArgumentException("row and column is not in the range of [0, side - 1]");
        }
        return weightedQuickUnionUFWithoutBOTTOM.connected(virtualTop, grid[row][col].index);
    }

    public boolean percolates() {
        return weightedQuickUnionUF.connected(virtualTop, virtualBottom);
    }
}
