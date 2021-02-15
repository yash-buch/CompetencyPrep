package problems.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IPO {
    public int findMaximizedCapital(int k, int W, int[] Profits, int[] Capital) {
        Map<Integer, Integer> map = new HashMap<>();
        int profit = W;
        for (int i = 0; i < Profits.length; i++) {
            int p = Profits[i];
            int c = Capital[i];
            int net = p - c;
            map.put(i, net);
        }
        for (int i = 0; i < k; i++) {
            //find project with capital less than wealth
            List<Integer> projects = findProjects(W, Capital);
            if (projects.size() == 0) break;
            int maxPMinusC = Integer.MIN_VALUE;
            int chosenProject = -1;
            for (int p : projects) {
                if (maxPMinusC <= map.get(p)) {
                    maxPMinusC = map.get(p);
                    chosenProject = p;
                }
            }
            //mark that project as done
            Capital[chosenProject] = Integer.MAX_VALUE;
            // calculate final wealth
            W = W + Profits[chosenProject];
            profit += Profits[chosenProject];
        }
        return profit;
    }

    private List<Integer> findProjects(int w, int[] capitals) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < capitals.length; i++) {
            if (w >= capitals[i]) list.add(i);
        }
        return list;
    }

    @Test
    public void testIPO() {
        IPO obj = new IPO();
        int[] p = new int[]{1,2,3};
        int[] c = new int[]{11,12,13};
        int profit = obj.findMaximizedCapital(11, 11, p, c);
        System.out.println(profit);
        assert 17 == profit;
    }
}
