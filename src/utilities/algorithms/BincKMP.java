package utilities.algorithms;

import java.util.ArrayList;
import java.util.List;

public class BincKMP {
    public List<Integer> matchPattern(String txt, String pat) {
        List<Integer> result = new ArrayList<>();
        int[] lps = new int[pat.length()];
        computeLPSArray(pat, lps);
        int j = 0;
        int i = 0;
        while(i < txt.length()) {
            if(txt.charAt(i) == pat.charAt(j)) {
                i++;
                j++;
            }
            if(j == pat.length()) {
                result.add(i-j);
                j = lps[j-1];
            } else if(i < txt.length() && txt.charAt(i) != pat.charAt(j)) {
                if(j != 0) {
                    j = lps[j-1];
                } else {
                    i = i + 1;
                }
            }
        }
        return result;
    }

    private void computeLPSArray(String pat, int[] lps) {
        int len = 0;
        int i = 1;
        lps[0] = 0;

        while(i < pat.length()) {
            if(pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if(len != 0) {
                    len = lps[len-1];
                } else {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        BincKMP bincKMP = new BincKMP();
        List<Integer> list = bincKMP.matchPattern("abcs","a");
        for(Integer i : list) {
            System.out.print(i+" ");
        }
    }
}
