package problems.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class RussianDoll {
    public int maxEnvelopes(int[][] envelopes) {
        Map<String, Integer> memo = new HashMap<>();
        int n = envelopes.length;
        int[] visited = new int[n];
        int result = 0;
        for(int i = 0; i < n; i++) {
            result = Math.max(result, dpUtil(envelopes, i, visited, memo));
        }
        return result;
    }

    private int dpUtil(int[][] env, int idx, int[] visited, Map<String, Integer> memo) {
        visited[idx] = 1;
        String hash = generateHash(visited);
        if (memo.containsKey(hash)) return memo.get(hash);
        int result = Integer.MIN_VALUE;

        for(int i = 0; i < visited.length; i++) {
            if (visited[i] == 0 && russianDollCond(idx, i, env)) {
                result = Math.max(result, 1+dpUtil(env, i, visited, memo));
            }
        }
        visited[idx] = 0;
        result = result == Integer.MIN_VALUE ? 0 : result;
        memo.put(hash, result);
        return result;
    }

    private boolean russianDollCond(int i, int j, int[][] env) {
        return env[i][0] > env[j][0] && env[i][1] > env[j][1];
    }

    private String generateHash(int[] a) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0) {
                sb.append(i);
            }
        }
        return sb.toString();
    }

    @Test
    public void testRussianDoll() {
        RussianDoll obj = new RussianDoll();
        int[][] env = new int[][]{{5,4},{6,4},{6,7},{2,3}};

        int res = obj.maxEnvelopes(env);
        System.out.println(res);

        assert res == 3;
    }
}
