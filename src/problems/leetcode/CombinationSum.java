package problems.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    List<List<Integer>> globalList;
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        globalList = new ArrayList<>();
        followTarget(0, target, candidates, new ArrayList<Integer>());
        return globalList;
    }

    private void followTarget(int idx, int target, int[] ar, List<Integer> list) {
        ArrayList<Integer> _list = new ArrayList<>(list);
        int num = ar[idx];
        if (target == num) {
            _list.add(num);
            globalList.add(_list);
            _list = new ArrayList<>(list);
        }
        for (int i = 0; i*num <= target && idx+1<ar.length; i++) {
            if (i > 0) {
                _list.add(num);
            }
            int revisedTarget = target - (i*num);
            if (revisedTarget == 0) {
                globalList.add(_list);
                _list = new ArrayList<>(list);
            } else
                followTarget(idx+1, revisedTarget, ar, _list);
        }
    }

    @Test
    public void testCombinationSum() {
        CombinationSum obj = new CombinationSum();
        int[] candidates = new int[]{2,3,5};
        int target = 8;
        List<List<Integer>> result = obj.combinationSum(candidates, target);

        for (List<Integer> list : result) {
            for(int i : list) {
                System.out.print(i+",");
            }
            System.out.println();
        }
    }
}
