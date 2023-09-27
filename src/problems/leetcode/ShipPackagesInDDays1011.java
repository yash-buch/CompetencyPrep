package problems.leetcode;

import org.junit.Test;

public class ShipPackagesInDDays1011 {
    int w;
    int[] wRange;
    int[][] memo;
    public int shipWithinDays(int[] weights, int days) {
        w = weights.length;
        wRange = new int[w];
        memo = new int[w][days+1];
        //cumulative weights
        wRange[0] = weights[0];
        for (int i = 1; i < w; i++) {
            wRange[i] = wRange[i-1]+weights[i];
        }
        int res = recur(weights, 0, days);
        return res;
    }

    private int recur(int[] weights, int idx, int daysLeft) {
        if (memo[idx][daysLeft] != 0) {
            return memo[idx][daysLeft];
        }
        if (daysLeft == 1) {
            memo[idx][daysLeft] = weightSum(idx, w-1, weights);
            return memo[idx][daysLeft];
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; idx+i <= w-daysLeft+1; i++) {
            min = Math.min(min, weightSum(idx, idx+i-1, weights)+
                            recur(weights, idx+i, daysLeft-1));
        }
        memo[idx][daysLeft] = min;
        return min;
    }

    private int weightSum(int i, int j, int[] weights) {
        return wRange[j]-wRange[i]+weights[i];
    }

    @Test
    public void test() {
        int[] in = new int[]{1,2,3,4,5,6,7,8,9,10};
        int days = 5;
        int res = new ShipPackagesInDDays1011().shipWithinDays(in, days);
        System.out.println(""+res);
    }
}
