package problems.leetcode;

import org.junit.Test;

public class NumberOfEnclaves {
    public int numEnclaves(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        int[][] visited = new int[m][n];
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == 0 && A[i][j] == 1) {
                    Pair pair = dfs(i, j, A, visited);
                    if (pair.canReachBorder) count += pair.count;
                }
            }
        }
        return count;
    }

    private Pair dfs(int i, int j, int[][] A, int[][] visited) {
        int m = A.length;
        int n = A[0].length;
        if (outOfBounds(i, j, m, n)) return new Pair(0, true);
        if (A[i][j] == 0) return new Pair(0, false);
        visited[i][j] = 1;
        Pair top = new Pair(0, false);
        if (!outOfBounds(i, j, m, n) && visited[i-1][j] != 1) top = dfs(i-1, j, A, visited);
        Pair right = new Pair(0, false);
        if (!outOfBounds(i, j, m, n) && visited[i][j+1] != 1) right = dfs(i, j+1, A, visited);
        Pair down = new Pair(0, false);
        if (!outOfBounds(i, j, m, n) && visited[i+1][j] != 1) down = dfs(i+1, j, A, visited);
        Pair left = new Pair(0, false);
        if (!outOfBounds(i, j, m, n) && visited[i][j-1] != 1) left = dfs(i, j-1, A, visited);
        boolean canReachBorder = top.canReachBorder || right.canReachBorder
                || down.canReachBorder || left.canReachBorder;
        int count = top.count + right.count + down.count + left.count + 1;
        return new Pair(count, canReachBorder);
    }

    private boolean outOfBounds(int i, int j, int m, int n) {
        return i < 0 || i >= m || j < 0 || j >= n;
    }

    class Pair {
        int count;
        boolean canReachBorder;
        public Pair (int c, boolean cnRchBrdr) {
            count = c;
            canReachBorder = cnRchBrdr;
        }
    }

    @Test
    public void testNumberOfEnclaves() {
        NumberOfEnclaves obj = new NumberOfEnclaves();
        int[][] A = new int[][]{{0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0}};
        int count = obj.numEnclaves(A);
        assert count == 1;
    }
}
