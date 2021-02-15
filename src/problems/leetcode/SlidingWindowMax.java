package problems.leetcode;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SlidingWindowMax {
    static int[] _nums;
    public int[] maxSlidingWindow(int[] nums, int k) {
        _nums = nums;
        int[] result = new int[nums.length - k + 1];
        PriorityQueue<Integer> q = new PriorityQueue<>(new IndexComparator());
        for (int i = 0; i < k; i++) {
            q.offer(i);
        }
        int idx = 0;
        result[idx++] = _nums[q.peek()];
        for (int i = 1; i + k - 1 < nums.length; i++) {
            int newIdx = i + k - 1;
            q.offer(newIdx);
            int maxIdx = q.peek();
            while (maxIdx < i) {
                q.poll();
                maxIdx = q.peek();
            }
            maxIdx = q.peek() == null ? i : q.peek();
            result[idx++] = _nums[maxIdx];
        }
        return result;
    }

    static class IndexComparator implements Comparator<Integer> {
        public int compare(Integer a, Integer b) {
            if (_nums[a] > _nums[b]) return -1;
            else if (_nums[a] == _nums[b]) return 0;
            return 1;
        }
    }

    @Test
    public void testSlidingWinMAx() {
        SlidingWindowMax obj = new SlidingWindowMax();
        int[] in = new int[]{7, 2, 4};
        int k = 2;

        int[] res = obj.maxSlidingWindow(in, k);
        int[] ex = new int[]{7, 4};

        assert Arrays.equals(ex, res);
    }
}
