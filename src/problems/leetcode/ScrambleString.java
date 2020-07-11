package problems.leetcode;

import org.junit.Test;

public class ScrambleString {

    public boolean isScramble(String s1, String s2) {
        boolean result = false;
        if (null == s1 || null == s2) return false;
        int len = s2.length();
        int[] aux1 = new int[26];
        int[] aux2 = new int[26];
        char[] ca1 = s1.toCharArray();
        char[] ca2 = s2.toCharArray();
        for(int i = 0; i < len; i++) {
            char c1 = ca1[i];
            aux1[c1-'a'] = i;

            char c2 = ca2[i];
            aux2[c2-'a'] = i;
        }
        int[][] aux = new int[len][len];
        int prevIdx = aux1[ca2[0]];
        int swapAtIdx = -1;
        for (int i = 1; i < len; i++) {
            int curIdx = aux1[ca2[i]];
        }
        return result;
    }

    @Test
    public void test_isScrambleWithNullInput() {
        ScrambleString obj = new ScrambleString();
        assert !obj.isScramble(null, null);
    }

    @Test
    public void test_isScrambleWith() {
        ScrambleString obj = new ScrambleString();
        assert obj.isScramble("great", "rgeat");

        assert !obj.isScramble("abcde", "caebd");
    }
}
