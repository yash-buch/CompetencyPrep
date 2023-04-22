package problems.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EventualSafeState802 {
    int[] memo;
    List<Integer> result;
    static final int SAFE = 1;
    static final int UNSAFE = 2;
    static final int UNDEF = 0;
    public List<Integer> eventualSafeNodes(int[][] graph) {
        memo = new int[graph.length];
        result = new ArrayList<>();
        boolean[] visited = new boolean[graph.length];
        for (int i = 0; i < graph.length; i++) {
            dfs(graph, i, visited);
        }
        Collections.sort(result);
        return result;
    }

    private boolean dfs(int[][] graph, int idx, boolean[] visited) {
        if (memo[idx] != UNDEF) {
            return memo[idx] == SAFE;
        }
        visited[idx] = true;
        boolean isSafe = true;
        for (int i = 0; i < graph[idx].length; i++) {
            int node = graph[idx][i];
            if (!visited[node]) {
                isSafe = isSafe && dfs(graph, node, visited);
                if (!isSafe) {
                    break;
                }
            } else {
                isSafe = false;
            }
        }
        if (isSafe) {
            result.add(idx);
        }
        visited[idx] = false;
        memo[idx] = isSafe ? SAFE : UNSAFE;
        return memo[idx] == SAFE;
    }

    @Test
    public void test() {
        EventualSafeState802 obj = new EventualSafeState802();
        int[][] graph = new int[][]{{1,2,3,4},{1,2},{3,4},{0,4},{}};
        List<Integer> res = obj.eventualSafeNodes(graph);
        System.out.println(res);
    }
}
