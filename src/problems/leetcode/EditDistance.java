package problems.leetcode;

import org.junit.Test;

public class EditDistance {
    private static final int INSERT_OR_DELETE = 0;
    private static final int REPLACE = 2;

    public int minDistance(String word1, String word2) {
        char[] chars1;
        char[] chars2;
        if (word1.length() >= word2.length()) {
            chars1 = word1.toCharArray();
            chars2 = word2.toCharArray();
        } else {
            chars1 = word2.toCharArray();
            chars2 = word1.toCharArray();
        }

        int r = chars2.length;
        int c = chars1.length;
        if (r == 0) {
            return c;
        }
        int[][] memo = new int[r+1][c+1];

        for (int i = 1; i <= r; i++) {
            memo[i][0] = i;
        }
        for (int j = 1; j <= c; j++) {
            memo[0][j] = j;
        }

        for (int i = 1; i <= r; i++) {
            for (int j = 1; j <= c; j++) {
                int min = Math.min(Math.min(memo[i-1][j], memo[i][j-1]), memo[i-1][j-1]);
                int operation = min == memo[i-1][j-1] ? REPLACE : INSERT_OR_DELETE;
                switch(operation) {
                    case REPLACE : {
                        if(chars1[j-1] == chars2[i-1]) {
                            memo[i][j] = min;
                        } else {
                            memo[i][j] = min + 1;
                        }
                        break;
                    }
                    case INSERT_OR_DELETE : {
                        memo[i][j] = min + 1;
                        break;
                    }
                }
            }
        }
        return memo[r][c];
    }

    @Test
    public void test_whenWordALongerThanB() {
        EditDistance obj = new EditDistance();
        String word1 = "teacher";
        String word2 = "attache";
        int dist = obj.minDistance(word1, word2);
        System.out.println(dist);
        assert (3 == dist);
    }
}
