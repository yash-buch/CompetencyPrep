package problems.leetcode;

import org.junit.Test;

public class PerfectSquares {
    public int numSquares(int n) {
        int[] memo = new int[n+1];
        return dpUtil(n, memo);
    }

    private int dpUtil(int n, int[] memo) {
        if (n <= 0) return 0;
        if (memo[n] != 0) return memo[n];
        int sqrt = (int)Math.sqrt(n);
        int result = Integer.MAX_VALUE;
        for (int i = sqrt; i > 0; i--) {
            result = Math.min(result, dpUtil(n-i*i, memo));
        }
        result++;
        memo[n] = result;
        return memo[n];
    }

    @Test
    public void testPerfectSquares() {
        PerfectSquares obj = new PerfectSquares();
        assert 3 == obj.numSquares(12);
    }
}
