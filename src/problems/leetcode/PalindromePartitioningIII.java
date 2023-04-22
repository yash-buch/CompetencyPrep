package problems.leetcode;

import org.junit.Test;

public class PalindromePartitioningIII {
    public int palindromePartition(String s, int k) {
        int len = s.length();
        int[][] memo = new int[len + 1][len + 1];
        preprocess(memo);
        int res = dfs(s, k - 1, memo, 0, len);
        print(memo);
        return res;
    }

    private int dfs(String s, int cutsLeft, int[][] memo, int l, int r) {
        //System.out.println(">"+s.substring(l,r)+":"+cutsLeft);
        int sLen = r - l;
        if (sLen <= cutsLeft) {
            return Integer.MAX_VALUE;
        }
        if (cutsLeft == 0) {
            if (memo[l][r] == -1) {
                memo[l][r] = flipCount(s.substring(l, r));
            }
            return memo[l][r];
        }
        int minLR = Integer.MAX_VALUE;
        for (int i = l + 1; i <= r; i++) {
            int min = memo[l][i] == -1 ? flipCount(s.substring(l, i)) : memo[l][i];
            memo[l][i] = min;
            //System.out.println(s.substring(l,i)+":"+cutsLeft);
            int min2 = dfs(s, cutsLeft - 1, memo, i, r);
            min = min2 != Integer.MAX_VALUE ? min + min2 : Integer.MAX_VALUE;
            minLR = Math.min(min, minLR);
            System.out.println("(" + l + "," + r + "):" + minLR);
        }
        return minLR;
    }

    private int flipCount(String s) {
        int i = 0;
        int j = s.length() - 1;
        int count = 0;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) {
                count++;
            }
            i++;
            j--;
        }
        return count;
    }

    private void preprocess(int[][] memo) {
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                memo[i][j] = -1;
            }
        }
    }

    private void print(int[][] memo) {
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                System.out.print(memo[i][j] + ",");
            }
            System.out.println();
        }
    }

    @Test
    public void testPP3() {
        PalindromePartitioningIII obj = new PalindromePartitioningIII();
        String in = "aabbc";
        int k = 3;
        int res = obj.palindromePartition(in, k);
        assert res == 0;
    }
}
