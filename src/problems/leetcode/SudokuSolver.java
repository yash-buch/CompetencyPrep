package problems.leetcode;

import org.junit.Test;

public class SudokuSolver {
    static final int FIXED = 2;
    int count = 0;
    public void solveSudoku(char[][] board) {
        int[][] auxRow = new int[9][10];
        int[][] auxCol = new int[9][10];
        int[][] blocks = new int[9][10];
        preProcess(board, auxRow, auxCol, blocks);
        process(0, 0, board, auxRow, auxCol, blocks);
        printBoard(board);
        System.out.print(count);
    }

    private boolean process(int i, int j, char[][] board, int[][] auxRow, int[][] auxCol, int[][] blocks) {
        count++;
        int jNext = (j+1)%board.length;
        int iNext = jNext == 0 ? i + 1 : i;
        /*if(iNext == 9) {
            return true;
        }*/
        if(board[i][j] != '.') {
            return iNext == 9 ? true : process(iNext, jNext, board, auxRow, auxCol, blocks);
        } else {
            for (int n = 1; n < 10; n++) {
                if (validate(n, i, j, auxRow, auxCol, blocks)) {
                    auxRow[i][n] = 1;
                    auxCol[j][n] = 1;
                    blocks[getBlockNumber(i, j)][n] = 1;
                    board[i][j] = (char)(n+'0');
                    boolean res = iNext == 9 ? true : process(iNext, jNext, board, auxRow, auxCol, blocks);
                    if (!res) {
                        auxRow[i][n] = 0;
                        auxCol[j][n] = 0;
                        blocks[getBlockNumber(i, j)][n] = 0;
                        board[i][j] = '.';
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean validate(int num, int i, int j, int[][] auxRow, int[][] auxCol, int[][] blocks) {
        boolean isValid = true;
        if (auxRow[i][num] > 0)
            isValid = false;
        if (auxCol[j][num] > 0)
            isValid = false;
        if (blocks[getBlockNumber(i,j)][num] > 0)
            isValid = false;
        return isValid;
    }

    private void preProcess(char[][] board, int[][] auxRow, int[][] auxCol, int[][] blocks) {
        int n = board.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - 48;
                    auxRow[i][num] = FIXED;
                    auxCol[j][num] = FIXED;
                    blocks[getBlockNumber(i, j)][num] = FIXED;
                }
            }
        }
    }

    private int getBlockNumber(int i, int j) {
        int block = 0;
        if (i < 3 && j < 3) {
            block = 0;
        } else if (i < 3 && j < 6) {
            block = 1;
        } else if (i < 3) {
            block = 2;
        } else if (i < 6 && j < 3) {
            block = 3;
        } else if (i < 6 && j < 6) {
            block = 4;
        } else if (i < 6 && j < 9) {
            block = 5;
        } else if (j < 3) {
            block = 6;
        } else if (j < 6) {
            block = 7;
        } else if (j < 9) {
            block = 8;
        }
        return block;
    }

    private void printBoard(char[][] board) {
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SudokuSolver obj = new SudokuSolver();
        /*char[][] board = new char[][]{{'5','3','.','.','7','.','.','.','.'},
                                      {'6','.','.','1','9','5','.','.','.'},
                                      {'.','9','8','.','.','.','.','6','.'},
                                      {'8','.','.','.','6','.','.','.','3'},
                                      {'4','.','.','8','.','3','.','.','1'},
                                      {'7','.','.','.','2','.','.','.','6'},
                                      {'.','6','.','.','.','.','2','8','.'},
                                      {'.','.','.','4','1','9','.','.','5'},
                                      {'.','.','.','.','8','.','.','7','9'}};*/
        /*char[][] board = new char[][]{{'.','.','9','7','4','8','.','.','.'},
                                      {'7','.','.','.','.','.','.','.','.'},
                                      {'.','2','.','1','.','9','.','.','.'},
                                      {'.','.','7','.','.','.','2','4','.'},
                                      {'.','6','4','.','1','.','5','9','.'},
                                      {'.','9','8','.','.','.','3','.','.'},
                                      {'.','.','.','8','.','3','.','2','.'},
                                      {'.','.','.','.','.','.','.','.','6'},
                                      {'.','.','.','2','7','5','9','.','.'}};*/
        char[][] board = new char[][]{{'.','.','.','.','.','.','.','.','.'},
                                      {'.','.','.','.','.','.','.','.','.'},
                                      {'.','.','.','.','.','.','.','.','.'},
                                      {'.','.','.','.','.','.','.','.','.'},
                                      {'.','.','.','.','.','.','.','.','.'},
                                      {'.','.','.','.','.','.','.','.','.'},
                                      {'.','.','.','.','.','.','.','.','.'},
                                      {'.','.','.','.','.','.','.','.','.'},
                                      {'.','.','.','.','.','.','.','.','.'}};
        obj.solveSudoku(board);
    }


    @Test
    public void test_getBlockNumber() {
        SudokuSolver obj = new SudokuSolver();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                assert(0 == obj.getBlockNumber(i, j));
            }
            for(int j = 3; j < 6; j++) {
                assert(1 == obj.getBlockNumber(i, j));
            }
            for(int j = 6; j < 9; j++) {
                assert(2 == obj.getBlockNumber(i, j));
            }
        }
        for(int i = 3; i < 6; i++) {
            for(int j = 0; j < 3; j++) {
                assert(3 == obj.getBlockNumber(i, j));
            }
            for(int j = 3; j < 6; j++) {
                assert(4 == obj.getBlockNumber(i, j));
            }
            for(int j = 6; j < 9; j++) {
                assert(5 == obj.getBlockNumber(i, j));
            }
        }
        for(int i = 6; i < 9; i++) {
            for(int j = 0; j < 3; j++) {
                assert(6 == obj.getBlockNumber(i, j));
            }
            for(int j = 3; j < 6; j++) {
                assert(7 == obj.getBlockNumber(i, j));
            }
            for(int j = 6; j < 9; j++) {
                assert(8 == obj.getBlockNumber(i, j));
            }
        }
    }
}
