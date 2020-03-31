//Substring with Concatenation of All Words : https://leetcode.com/problems/substring-with-concatenation-of-all-words/
package problems.leetcode;

import utilities.algorithms.BincKMP;

import java.util.ArrayList;
import java.util.List;

public class ProblemSWCOAW {


    public static void main(String[] args) {
        String s = "wordgoodgoodgoodbestword";
        String[] words = new String[]{"word","good","best","word"};
        ProblemSWCOAW obj = new ProblemSWCOAW();
        List<Integer> list = obj.findSubstring("", new String[0]);
        for(Integer in : list) {
            System.out.println(in+"");
        }
    }

    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if(s == null || s.length() == 0 || words == null || words.length == 0)
            return result;
        int wordSize = words[0].length();
        List<Integer>[] wordMatches = new List[s.length()];
        buildMaps(wordMatches, s, words);
        for(int i = 0; i < wordMatches.length; i++) {
            boolean shouldAdd = process(wordMatches, i, wordSize, words.length);
            if (shouldAdd)
                result.add(i);
        }

        return result;
    }

    private boolean process(List<Integer>[] wordMatches, int index, int wordSize, int numWords) {
        if(wordMatches[index] == null)
            return false;
        final int USED = 1;
        int[] usedWords = new int[numWords];
        int matchedWordCount = 0;
        int loopCount = 0;
        for(int i = index; i < wordMatches.length && matchedWordCount < numWords; i += wordSize) {
            loopCount++;
            List<Integer> wordList = wordMatches[i];
            if (wordList == null)
                return false;
            for(Integer a : wordList) {
                if (usedWords[a] == USED) {
                    continue;
                }
                usedWords[a] = USED;
                matchedWordCount++;
                break;
            }
            if (loopCount != matchedWordCount)
                break;
        }
        return matchedWordCount == numWords;
    }

    private void buildMaps(List<Integer>[] wordMatches, String s, String[] words) {
        BincKMP kmp = new BincKMP();
        for(int i = 0; i < words.length; i++) {
            String word = words[i];
            List<Integer> wordOccurrences = kmp.matchPattern(s, word);
            for(Integer occurrence : wordOccurrences) {
                if(wordMatches[occurrence] == null) {
                    wordMatches[occurrence] = new ArrayList<>();
                }
                wordMatches[occurrence].add(i);
            }
        }
    }
}
