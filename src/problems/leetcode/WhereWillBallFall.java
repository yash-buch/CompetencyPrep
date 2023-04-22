package problems.leetcode;

import org.junit.Test;

public class WhereWillBallFall {

    int m = 0;
    int n = 0;

    public int[] findBall(int[][] grid) {
        int[] result = null;
        m = grid.length;
        if (m == 0) return result;
        n = grid[0].length;
        result = new int[n];
        for (int i = 0; i < n; i++) {
            int col = i;
            for (int j = 0; j < m; j++) {
                if (!canMoveToNxtRow(grid, col, j)) {
                    result[i] = -1;
                    break;
                } else {
                    if (grid[j][col] == 1) {
                        col = col + 1;
                    } else {
                        col = col - 1;
                    }
                }
            }

            if (result[i] != -1) {
                result[i] = col;
            }
        }
        return result;
    }

    boolean canMoveToNxtRow(int[][] grid, int col, int row) {
        if (grid[row][col] == 1) {
            if (col >= n - 1 || grid[row][col + 1] == -1) return false;
        } else {
            if (col == 0 || grid[row][col - 1] == 1) return false;
        }
        return true;
    }

    @Test
    public void testSolution() {
        WhereWillBallFall obj = new WhereWillBallFall();
        int[][] grid = new int[][]{{1, 1, 1, -1, -1},
                {1, 1, 1, -1, -1},
                {-1, -1, -1, 1, 1},
                {1, 1, 1, 1, -1},
                {-1, -1, -1, -1, -1}};

        int[] res = obj.findBall(grid);
        for (int i : res) {
            System.out.print(i+",");
        }
    }
}
