package problems.leetcode;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class KthLargest {
    public int findKthLargest(int[] nums, int k) {
        //initialize priorityQueue of size k. it should emulate min-heap
        Queue<Integer> pq = new PriorityQueue<>(k, new MinComparator());
        //Got through the array and insert the nums to the queue.
        //If the queue is full pop first then push.
        for (int num : nums) {
            if (pq.size() == k) {
                if (pq.element() < num) {
                    pq.remove();
                    pq.add(num);
                }
            } else {
                pq.add(num);
            }
        }
        // the top element is the answer
        return pq.element();
    }

    static class MinComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return -1*(o2.compareTo(o1));
        }
    }

    @Test
    public void testKthLargest() {
        KthLargest obj = new KthLargest();
        int[] nums = new int[]{2,1};
        int k = 2;
        int res = obj.findKthLargest(nums, k);

        assert 1 == res;
    }
}
