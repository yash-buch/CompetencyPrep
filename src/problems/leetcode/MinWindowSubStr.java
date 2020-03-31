package problems.leetcode;

import org.junit.Test;
import utilities.datastructures.BincArrayQueue;
import utilities.exceptions.QueueOverFlowException;

import java.util.HashMap;
import java.util.Map;

public class MinWindowSubStr {
    private final int START = 0;
    private final int END = 1;
    int width = Integer.MAX_VALUE;
    int start = 0;
    int end = 0;

    public String minWindow(String s, String t) {
        Map<Character, Integer> charCountMap = new HashMap<>();
        int[] charMap = new int[26+26];
        buildCharCountMap(charCountMap, charMap, t);
        char[] sAr = s.toCharArray();
        int[] aux = new int[sAr.length];
        int[] curStartEnd = new int[2];
        curStartEnd[1] = -1;
        Map<Character, BincArrayQueue<Integer>> auxMap = new HashMap<>();
        for(int i = 0; i < sAr.length; i++) {
            if((sAr[i] >= 'A' && sAr[i] <= 'Z' && charMap[sAr[i] - 'A'] == 1) ||
                    (sAr[i] >= 'a' && sAr[i] <= 'z' && charMap[sAr[i] - 'a' + 26] == 1)) {
                processChar(sAr[i], charCountMap, auxMap, aux, curStartEnd, i);
            }
        }

        String result = "";
        if(width != Integer.MAX_VALUE) {
            result = s.substring(start, end+1);
        }
        return result;
    }

    private void processChar(char c, Map<Character, Integer> characterIntegerMap,
                             Map<Character, BincArrayQueue<Integer>> auxMap,
                             int[] aux, int[] curStartEnd, int index) {
        int startIdx = curStartEnd[START];
        int endIdx = curStartEnd[END];
        int auxIdx = endIdx+1;

        if (!auxMap.containsKey(c)) {
            auxMap.put(c, new BincArrayQueue(Integer.class, characterIntegerMap.get(c)));
        } else if (characterIntegerMap.get(c) == null || characterIntegerMap.get(c) == 0) {
            try {
                auxIdx = auxMap.get(c).dequeue();
            } catch (QueueOverFlowException e) {
                e.printStackTrace();
            }
        }

        if (auxIdx <= endIdx) {
            aux[auxIdx] *= -1;
            if(aux[auxIdx] == 0) aux[auxIdx] = -1;
            if(auxIdx == startIdx) {
                while(aux[startIdx] < 0) {
                    startIdx++;
                }
            }
            auxIdx = endIdx+1;
        }

        aux[auxIdx] = index;
        endIdx = auxIdx;

        try {
            auxMap.get(c).enqueque(auxIdx);
            if(characterIntegerMap.containsKey(c)) {
                characterIntegerMap.put(c, characterIntegerMap.get(c)-1);
                if(characterIntegerMap.get(c) == 0) {
                    characterIntegerMap.remove(c);
                }
            }
        } catch (QueueOverFlowException e) {
            e.printStackTrace();
        }

        if(characterIntegerMap.size() == 0) {
            if(width > (aux[endIdx] - aux[startIdx] + 1)) {
                width = (aux[endIdx] - aux[startIdx] + 1);
                start = aux[startIdx];
                end = aux[endIdx];
            }
        }

        curStartEnd[START] = startIdx;
        curStartEnd[END] = endIdx;
    }

    private void buildCharCountMap(Map<Character, Integer> map, int[] charMap, String t) {
        for(char c : t.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c)+1);
            } else {
                map.put(c, 1);
            }
            if(c >= 'A' && c <= 'Z')
                charMap[c-'A'] = 1;
            else {
                charMap[c - 'a' + 26] = 1;
            }
        }
    }



    @Test
    public void test_minWindow() {
        MinWindowSubStr obj = new MinWindowSubStr();
        String s = "ADoBECODEBANA";
        String t = "Ao";
        String result = obj.minWindow(s, t);
        System.out.print(result);
        assert ("ADo".equals(result));
    }
}
