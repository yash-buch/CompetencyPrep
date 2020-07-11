package problems.leetcode;

import org.junit.Test;

public class MaximalRectangle {

    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int[][] mat = new int[matrix.length][matrix[0].length];
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                    mat[i][j] = matrix[i][j] - '0';
            }
        }
        int maxArea = 0;
        LargestRectangle obj = new LargestRectangle();
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[0].length; j++) {
                if (i != 0 && mat[i][j] != 0) {
                    mat[i][j] = mat[i-1][j] + mat[i][j];
                }
            }
            maxArea = Math.max(maxArea, obj.largestRectangleArea(mat[i]));
        }
        return maxArea;
    }

    @Test
    public void test_maximalRectangle() {
        MaximalRectangle obj = new MaximalRectangle();
        char[][] mat = new char[][]{
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','0','1'},
                {'1','0','0','1','0'}
        };

        int result = obj.maximalRectangle(mat);

        assert result == 4;
    }
}
