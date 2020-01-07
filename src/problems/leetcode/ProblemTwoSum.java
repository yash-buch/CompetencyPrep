//https://leetcode.com/problems/two-sum/
package problems.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ProblemTwoSum {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ProblemTwoSum obj = new ProblemTwoSum();
        obj.printArray(obj.twoSum(new int[]{5, 3, 7, 11, 16}, 2));
        sc.close();
    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        populateMap(nums, map);
        int res1 = 0, res2 = 0;
        for (int index = 0; index < nums.length; index++) {
            int suppl = target - nums[index];
            if (map.containsKey(suppl) && index != map.get(suppl)) {
                res1 = index;
                res2 = map.get(suppl);
                break;
            }
        }
        return new int[]{res1, res2};
    }

    private void populateMap(int[] nums, Map<Integer, Integer> map) {
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
    }

    private void printArray(int[] arr) {
        System.out.print(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            System.out.print(" " + arr[i]);
        }
    }
}
