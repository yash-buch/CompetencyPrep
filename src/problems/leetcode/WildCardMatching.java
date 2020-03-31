package problems.leetcode;

public class WildCardMatching {
    public boolean isMatch(String s, String p) {
        if(s.length() == 0) {
            if(p.equals("*") || p.length() == 0) return true;
            else return false;
        }
        if(p.length() == 0) {
            return false;
        }
        char[] sArr = s.toCharArray();
        char[] pArr = p.toCharArray();
        int n = s.length() + 1;
        int m = p.length() + 1;
        int[][] memo = new int[m][n];
        boolean hasCharMatched = false;
        memo[0][0] = 1;
        for(int i = 1; i < m; i++) {
            char pCh = pArr[i-1];
            if(pCh == '*') {
                for(int j = 1; j < n; j++) {
                    if(memo[i-1][j-1] > 0 || memo[i-1][j] > 0 || memo[i][j-1] > 0) {
                        memo[i][j] = 1;
                    }
                }
                if(memo[i][1] > 0 && !hasCharMatched) memo[i][0] = 1;
            } else if(pCh == '?') {
                for(int j = 1; j < n; j++) {
                    if(memo[i-1][j-1] > 0) {
                        memo[i][j] = 1;
                        hasCharMatched = true;
                    }
                }
            } else {
                for(int j = 1; j < n; j++) {
                    if (sArr[j-1] == pCh && memo[i-1][j-1] > 0) {
                        memo[i][j] = 1;
                        hasCharMatched = true;
                    }
                }
            }
        }
        return memo[m-1][n-1] > 0;
    }

    public static void main(String[] args) {
        String s = "a";
        String p = "?*?";
        //String s = "ab";
        //String p = "a?*b";
        WildCardMatching obj = new WildCardMatching();
        System.out.println(obj.isMatch(s, p)+"");
    }
}
