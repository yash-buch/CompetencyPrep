package problems.leetcode;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StrangePrinterII {
    private static final int LEFT = 0;
    private static final int TOP = 1;
    private static final int RIGHT = 2;
    private static final int BOTTOM = 3;
    public boolean isPrintable(int[][] targetGrid) {
        int m = targetGrid.length;
        int n = targetGrid[0].length;
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int color = targetGrid[i][j];
                if (!map.containsKey(color)) {
                    map.put(color, new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE});
                }
                updateMapForKey(color, map, i, j);
            }
        }
        Map<Integer, Boolean> visited = new HashMap<>();

        for (int color : map.keySet()) {
            visited.put(color, false);
        }
        boolean isCycleFound = false;
        for (int color : map.keySet()) {
            if(!visited.get(color))
                isCycleFound = dfs(color, map, visited, new HashSet<>(), targetGrid);
            if (isCycleFound) break;
        }
        return !isCycleFound;
    }

    private boolean dfs(int color, Map<Integer, int[]> map, Map<Integer, Boolean> visited, Set<Integer> parents, int[][] targetGrid) {
        parents.add(color);
        visited.put(color, true);
        Set<Integer> children = getChildren(color, map, targetGrid);
        if (children.isEmpty()) return false;
        boolean isCycleFound = false;
        for (int child : children) {
            if (parents.contains(child) || isCycleFound) {
                isCycleFound = true;
                break;
            }
            isCycleFound = dfs(child, map, visited, parents, targetGrid);
        }
        return isCycleFound;
    }

    private Set<Integer> getChildren(int color, Map<Integer, int[]> map, int[][] targetGrid) {
        Set<Integer> children = new HashSet<>();
        int[] border = map.get(color);
        for (int i = border[TOP]; i <= border[BOTTOM]; i++) {
            for (int j = border[LEFT]; j <= border[RIGHT]; j++) {
                if (targetGrid[i][j] != color) {
                    children.add(targetGrid[i][j]);
                }
            }
        }
        return children;
    }

    private void updateMapForKey(int key, Map<Integer, int[]> map, int i, int j) {
        int[] border = map.get(key);
        border[LEFT] = Math.min(border[LEFT], j);
        border[RIGHT] = Math.max(border[RIGHT], j);
        border[TOP] = Math.min(border[TOP], i);
        border[BOTTOM] = Math.max(border[BOTTOM], i);
    }

    @Test
    public void testStrangePrinter() {
        int[][] grid = new int[][]{{1,1,1,1},{1,2,2,1},{1,2,2,1},{1,1,1,1}};
        StrangePrinterII obj = new StrangePrinterII();
        boolean res = obj.isPrintable(grid);
        assert res;
    }
}
