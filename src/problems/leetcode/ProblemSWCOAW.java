//Substring with Concatenation of All Words : https://leetcode.com/problems/substring-with-concatenation-of-all-words/
package problems.leetcode;

import utilities.algorithms.BincKMP;
import utilities.algorithms.BincPermutation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProblemSWCOAW {
    public List<Integer> findSubstring(String s, String[] words) {
        BincPermutation<Integer> bp = new BincPermutation<>();
        Integer[] idxArr = new Integer[words.length];
        for(int i = 0; i < words.length; i++) {
            idxArr[i] = i;
        }
        List<Integer[]> permutations = bp.permute(idxArr);
        List<String> strings = new ArrayList<>();
        for(Integer[] intArr : permutations) {
            StringBuffer sb = new StringBuffer();
            for(Integer i : intArr) {
                sb.append(words[i]);
            }
            strings.add(sb.toString());
        }
        BincKMP kmp = new BincKMP();
        List<Integer> result = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        for(String str : strings) {
            List<Integer> list = kmp.matchPattern(s, str);
            if(list.size() > 0) {
                set.addAll(list);
            }
        }
        for(Integer i : set) {
            result.add(i);
        }
        return result;
    }
}
