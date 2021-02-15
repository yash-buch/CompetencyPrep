package problems.eopi;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class LongestContainedInterval {
    public int longestContainedInterval(int[] in) { //Assumption: no repetition
        Map<Integer, Integer> map = new HashMap<>();
        int count = 1;
        for (int num : in) {
            map.put(num, 0);
        }

        for (int key : map.keySet()) {
            int _count;
            if (map.get(key) != 1) {
                _count = checkContinuity(map, key);
                count = Math.max(count, _count);
            }
        }
        return count;
    }

    private int checkContinuity(Map<Integer, Integer> map, int cur) {
        int count = 1;
        map.put(cur, 1);
        int nextKey = cur+1;
        while (map.containsKey(nextKey)) {
            map.put(nextKey, 1);
            count++;
            nextKey++;
        }
        return count;
    }

    @Test
    public void testLongestContainerInterval() {
        LongestContainedInterval obj = new LongestContainedInterval();
        int[] arr = new int[]{-2, 7, 9, 8, 1, 2, 0, -1, 5, 8};

        int count = obj.longestContainedInterval(arr);

        assert count == 5;
    }
}
