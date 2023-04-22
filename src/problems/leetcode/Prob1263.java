package problems.leetcode;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class Prob1263 {
    int m = 0;
    int n = 0;
    public int minPushBox(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        int[][] visited = new int[m][n];
        int[] box = new int[2];
        int[] target = new int[2];
        int[] man = new int[2];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 'T') {
                    target[0] = i;
                    target[1] = j;
                }
                if (grid[i][j] == 'S') {
                    man[0] = i;
                    man[1] = j;
                }
                if (grid[i][j] == 'B') {
                    box[0] = i;
                    box[1] = j;
                }
            }
        }

        dfs(grid, visited, man[0], man[1]);
        return bfs(grid, visited, box[0], box[1], target);
    }

    private int bfs(char[][] grid, int[][] visited, int i , int j, int[] target) {
        int[][] visitBox = new int[m][n];
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{i,j});
        visitBox[i][j] = 1;
        int steps = 0;
        int levelTot = 1;
        int levelCount = 0;
        int nxtLevelTot = 0;
        while(!q.isEmpty()) {
            int[] coord = q.remove();
            i = coord[0];
            j = coord[1];
            levelCount++;
            if (coord[0] == target[0] && coord[1] == target[1]) {
                return steps;
            }
            if (levelTot == levelCount) {
                levelCount = 0;
            }
            if (canVisit(i-1, j) && visitBox[i-1][j] == 0 && grid[i-1][j] != '#' &&
                    canVisit(i+1, j) && visited[i+1][j] == 1) {
                visitBox[i-1][j] = 1;
                q.offer(new int[]{i-1, j});
                nxtLevelTot++;
            }
            if (canVisit(i, j+1) && visitBox[i][j+1] == 0 && grid[i][j+1] != '#' &&
                    canVisit(i, j-1) && visited[i][j-1] == 1) {
                visitBox[i][j+1] = 1;
                q.offer(new int[]{i, j+1});
                nxtLevelTot++;
            }
            if (canVisit(i+1, j) && visitBox[i+1][j] == 0 && grid[i+1][j] != '#' &&
                    canVisit(i-1, j) && visited[i-1][j] == 1) {
                visitBox[i+1][j] = 1;
                q.offer(new int[]{i+1, j});
                nxtLevelTot++;
            }
            if (canVisit(i, j-1) && visitBox[i][j-1] == 0 && grid[i][j-1] != '#' &&
                    canVisit(i, j+1) && visited[i][j+1] == 1) {
                visitBox[i][j-1] = 1;
                q.offer(new int[]{i, j-1});
                nxtLevelTot++;
            }
            if (levelCount == 0) {
                levelTot = nxtLevelTot;
                nxtLevelTot = 0;
                steps++;
            }
        }
        return -1;
    }

    private void dfs(char[][] grid, int[][] visited, int i, int j) {
        visited[i][j] = 1;
        if (canVisit(i-1, j) && visited[i-1][j] == 0 && grid[i-1][j] != '#') {
            dfs(grid, visited, i-1, j);
        }
        if (canVisit(i, j-1) && visited[i][j-1] == 0 && grid[i][j-1] != '#') {
            dfs(grid, visited, i, j-1);
        }
        if (canVisit(i+1, j) && visited[i+1][j] == 0 && grid[i+1][j] != '#') {
            dfs(grid, visited, i+1, j);
        }
        if (canVisit(i, j+1) && visited[i][j+1] == 0 && grid[i][j+1] == '.') {
            dfs(grid, visited, i, j+1);
        }
    }

    private boolean canVisit(int i, int j) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }

    @Test
    public void test() {
        Prob1263 obj = new Prob1263();
        char[][] grid = new char[][]{{'#','#','#','#','#','#'},{'#','T','#','#','#','#'},{'#','.','.','B','.','#'},{'#','.','#','#','.','#'},{'#','.','.','.','S','#'},{'#','#','#','#','#','#'}};
        int res = obj.minPushBox(grid);
        System.out.println(res);
    }
}
