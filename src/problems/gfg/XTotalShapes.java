package problems.gfg;

import java.util.LinkedList;
import java.util.Queue;

public class XTotalShapes {
    public static void main (String[] args) {
        String[] arr = new String[]{"XXO", "OOX", "OXO", "OOO" ,"XOX" ,"XOX", "OXO", "XXO", "XXX", "OOO"};
        int res = Shape(arr, 10, 3);
        System.out.println(res);
    }

    public static int Shape(String []arr,int n,int m)
    {
        char[][] mat = new char[n][m];
        for (int i = 0; i < n; i++) {
            mat[i] = arr[i].toCharArray();
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 'X') {
                    count ++;
                    BFS(mat, i, j, n, m);
                }
            }
        }
        return count;
    }

    private static void BFS(char[][] mat, int i, int j, int n, int m) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(i, j));
        while(!q.isEmpty()) {
            Pair pair = q.remove();
            int a = pair.i;
            int b = pair.j;
            mat[a][b] = 'o';
            //top
            if ((a-1 >= 0) && mat[a-1][b] == 'X') q.add(new Pair(a-1,b));
            //right
            if ((b+1 < m) && mat[a][b+1] == 'X') q.add(new Pair(a, b+1));
            //bottom
            if ((a+1 < n) && mat[a+1][b] == 'X') q.add(new Pair(a+1, b));
            //left
            if ((b-1 >= 0) && mat[a][b-1] == 'X') q.add(new Pair(a, b-1));
        }
    }

    static class Pair {
        int i,j;
        Pair(int a, int b) {
            i = a;
            j = b;
        }
    }
}
