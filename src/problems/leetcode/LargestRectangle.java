package problems.leetcode;

import org.junit.Test;

import java.util.Stack;

public class LargestRectangle {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int[] aux = new int[heights.length];
        int result = 0;
        int idx = 0;
        performPush(stack, aux, idx++);

        while (!stack.isEmpty()) {
            if (idx < heights.length && heights[idx] > heights[idx - 1]) {
                //perform push
                performPush(stack, aux, idx);
            } else {
                //perform pop
                int res = performPop(stack, aux, heights, idx);
                result = Math.max(res, result);
                //perform push
                if (idx < heights.length)
                    performPush(stack, aux, idx);
            }
            idx++;
        }
        return result;
    }

    private void performPush(Stack<Integer> stack, int[] aux, int index) {
        stack.push(index);
        aux[index] += 1;
    }

    private int performPop(Stack<Integer> stack, int[] aux, int[] heights, int index) {
        int popCount = 0;
        int popCounter = 0;
        int max = 0;
        while (!stack.isEmpty() && (index >= heights.length || heights[stack.peek()] > heights[index])) {
            int poppedItemIdx = stack.pop();
            int tempMax = (aux[poppedItemIdx] + popCounter) * heights[poppedItemIdx];
            popCounter += aux[poppedItemIdx];
            max = Math.max(max, tempMax);
            popCount += aux[poppedItemIdx];
        }
        if (index < heights.length)
            aux[index] = popCount;
        return max;
    }

    @Test
    public void test_largestRectangle() {
        LargestRectangle obj = new LargestRectangle();
        int[] in = new int[]{2,1,5,4,6,2,3};
        int result = obj.largestRectangleArea(in);
        System.out.print(result);
        assert (result == 12);
    }
}
