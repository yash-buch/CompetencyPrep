//https://practice.geeksforgeeks.org/problems/relative-sorting/0
package problems.gfg;
/*package whatever //do not write package name here */

import java.util.*;
import java.lang.*;
import java.io.*;

class ProblemOne {
    public static void main (String[] args) {
        //code
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        for (int t = 0; t <  testCases; t++) {
            ProblemOne problemOne = new ProblemOne();
            int N = sc.nextInt();
            int M = sc.nextInt();
            int[] A1 = new int[N];
            int[] A2 = new int[M];
            for(int i = 0; i < N; i++) {
                A1[i] = sc.nextInt();
            }
            for(int i = 0; i < M; i++) {
                A2[i] = sc.nextInt();
            }
            Map<Integer, Integer> map = new HashMap<>();
            problemOne.preProcess(A1, map);
            Map<Integer, Integer> secondaryMap = new HashMap<>();
            problemOne.preProcess(A2, secondaryMap);
            int[] resArr = new int[N];
            int index = problemOne.evaluate(resArr, A2, map);
            problemOne.evaluateRest(index, resArr, A1, secondaryMap);
            System.out.print(resArr[0]);
            for(int i = 1; i < resArr.length; i++) {
                System.out.print(" "+resArr[i]);
            }
            System.out.println();
        }
    }

    private void evaluateRest(int index, int[] res, int[] in, Map<Integer, Integer> map){
        int pendingNums = in.length - index;
        int[] restNums = new int[pendingNums];
        int j = 0;
        for(int i = 0; i < in.length; i++) {
            if(!map.containsKey(in[i])) {
                restNums[j] = in[i];
                j++;
            }
        }
        Arrays.sort(restNums);
        for(int i = 0; i < restNums.length; i++) {
            res[index+i] = restNums[i];
        }
    }

    private int evaluate(int[] res, int[] secondaryArr, Map<Integer, Integer> map) {
        int index = 0;
        for(int i = 0; i < secondaryArr.length; i++) {
            int key = secondaryArr[i];
            int numCount = map.get(key);
            for(int j = 0; j < numCount; j++) {
                res[index] = key;
                index++;
            }
        }
        return index;
    }

    private void preProcess(int[] in, Map<Integer, Integer> map) {
        for(int i = 0; i < in.length; i++) {
            int key = in[i];
            if(map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }
    }
}