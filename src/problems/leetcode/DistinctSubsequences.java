package problems.leetcode;

import org.junit.Test;

public class DistinctSubsequences {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        int[][] memo = new int[n+1][m+1];
        for (int j = 1; j <= m; j++) {
            if (t.charAt(0) == s.charAt(j-1)) {
                memo[0][j-1] = 1;
            }
        }
        for (int i = 1; i <= n; i++) {
            for (int j = i; j <= m; j++) {
                char cs = s.charAt(j-1);
                char ct = t.charAt(i-1);
                if (cs == ct) {
                    memo[i][j] = memo[i-1][j-1] + memo[i][j-1];
                } else {
                    memo[i][j] = memo[i][j-1];
                }
            }
        }
        return memo[n][m];

    }

    @Test
    public void testDistinctSunSeqs() {
        DistinctSubsequences obj = new DistinctSubsequences();
        String s = "babgbag";
        String t = "bag";

        int count  = obj.numDistinct(s, t);

        assert 5 == count;
    }
}
