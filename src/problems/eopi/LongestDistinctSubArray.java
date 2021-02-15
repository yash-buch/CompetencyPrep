package problems.eopi;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class LongestDistinctSubArray {
    public int longestDistinctSubArray(char[] in) {
        int count = 0;
        Map<Character, Integer> map = new HashMap<>();
        int _count = 0;
        int lastDiscardedIdx = -2;
        for (int i = 0; i < in.length;) {
            char c = in[i];
            if (map.containsKey(c) && map.get(c) > lastDiscardedIdx+1) {
                count = Math.max(count, _count);
                _count = 0;
                i = map.get(c) + 1;
                lastDiscardedIdx = map.get(c);
            } else {
                map.put(c, i);
                _count++;
                i++;
            }
        }

        return Math.max(count, _count);
    }


    @Test
    public void testLongestDistinctSubArray() {
        LongestDistinctSubArray obj = new LongestDistinctSubArray();

        char[] chars;
        chars = new char[]{'f','s','f','e','t','w','e','n','w','e'};
        int count = obj.longestDistinctSubArray(chars);
        assert 5 == count;
    }
}
