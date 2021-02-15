package problems.leetcode;

import org.junit.Test;

public class MaximalSquare {

    public int maximalSquare(char[][] matrix) {
        int area = Integer.MIN_VALUE;
        int m = matrix.length;
        int n = matrix[0].length;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if (matrix[i][j] == '1')
                    area = Math.max(area, squareArea(i, j, matrix));
            }
        }
        return area;
    }

    private int squareArea(int i, int j, char[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int width = 90001;
        int height = 0;
        int a = i;
        int b = j;
        while (a < m && mat[a][j] == '1' && a < width+i) {
            height++;
            int w = 0;
            while (b < n && mat[i][b] == '1' && b < width+j) {
                w++;
                b++;
            }
            b = j;
            width = Math.min(width, w);
            a++;
        }
        int len = Math.min(height, width);
        for(a = i; a < len; a++) {
            for(b = j; b < len; b++) {
                mat[a][b] = '0';
            }
        }
        return len*len;
    }

    @Test
    public void testMaximalSquare() {
        MaximalSquare obj = new MaximalSquare();
        char[][] mat = new char[][]{{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        int res = obj.maximalSquare(mat);

        System.out.println(res);

        assert 4 == res;
    }
}
