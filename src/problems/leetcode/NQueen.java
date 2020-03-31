package problems.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NQueen {
    List<List<String>> result = new ArrayList();
    boolean shouldReverse = true;
    public List<List<String>> solveNQueens(int n) {
        for(int i = 0; i < n/2; i++) {
            int[][] aux = new int[n][n];
            Map<Integer, Object> cm = new HashMap<>();
            Map<Integer, Object> rm = new HashMap<>();
            rm.put(0, null);
            cm.put(i, null);
            aux[0][i] = 1;
            boolean res = solveNextRow(n, 1, aux, cm, rm);
            if(res) {
                commitResult(n, aux);
            }
        }
        if(n%2 == 1) {
            shouldReverse = false;
            int i = (int)Math.ceil(n/2);
            int[][] aux = new int[n][n];
            Map<Integer, Object> cm = new HashMap<>();
            Map<Integer, Object> rm = new HashMap<>();
            rm.put(0, null);
            cm.put(i, null);
            aux[0][i] = 1;
            solveNextRow(n, 1, aux, cm, rm);
        }
        return result;
    }

    private void commitResult(int n, int[][] aux) {
        List<String> list = new ArrayList();
        for(int a = 0; a < n; a++) {
            StringBuffer sb = new StringBuffer();
            for(int b = 0; b < n; b++) {
                if(aux[a][b] == 1) sb.append('Q');
                else sb.append('.');
            }
            list.add(sb.toString());
        }
        result.add(list);
        if(shouldReverse)
            result.add(reverse(n, aux));
    }

    private boolean solveNextRow(int n, int r, int[][] aux, Map<Integer, Object> cm, Map<Integer, Object> rm) {
        if(r >= n) {
            commitResult(n, aux);
            return true;
        }
        for(int i = 0; i < n; i++) {
            if(!isConflicting(n, r, i, aux, cm, rm)) {
                rm.put(r, null);
                cm.put(i, null);
                aux[r][i] = 1;
                solveNextRow(n, r+1, aux, cm, rm);
                aux[r][i] = 0;
                rm.remove(r);
                cm.remove(i);
            }
        }
        return false;
    }

    private List<String> reverse(int n, int[][] aux) {
        List<String> list = new ArrayList();
        for(int a = 0; a < n; a++) {
            StringBuffer sb = new StringBuffer();
            for(int b = 0; b < n; b++) {
                if(aux[a][b] == 1) sb.append('Q');
                else sb.append('.');
            }
            list.add(sb.reverse().toString());
        }
        return list;
    }

    private boolean isConflicting(int n, int r, int c, int[][] aux, Map<Integer, Object> cm, Map<Integer, Object> rm) {
        if(cm.containsKey(c)) {
            return true;
        }
        if(rm.containsKey(r)) {
            return true;
        }
        int i = r-1;
        int j = c-1;
        while(i>=0 && j>=0) {
            if(aux[i][j] == 1) return true;
            i--;
            j--;
        }
        i = r-1;
        j = c+1;
        while(i>=0 && j<n) {
            if(aux[i][j] == 1) return true;
            i--;
            j++;
        }
        return false;
    }

    public static void main(String[] args) {
        NQueen obj = new NQueen();
        List<List<String>> res = obj.solveNQueens(5);
        for(List<String> list : res) {
            for(String str : list) {
                System.out.println(str);
            }
            System.out.println("--------------------");
        }
    }
}
