package problems.codility;

import java.util.HashMap;
import java.util.Map;

public class ProblemZalando3 {

    public static void main(String[] args) {
        //Scanner sc = new Scanner(System.in);
        int[] n = new int[100000];
        for(int i = 0; i < 100000; i++) {
            n[i] = 0;
        }
        //int[] n = new int[]{0,0,0};
        int result = new ProblemZalando3().solution(n);
        System.out.print(result);
    }

//    public int solution(int[] A) {
//        // write your code in Java SE 8
//        int n = A.length;
//        int count = 0;
//        long[][] sumMatrix = new long[n][n];
//        for(int i = 0; i < n; i++) {
//            if(A[i] == 0) count++;
//            sumMatrix[i][i] = A[i];
//        }
//        for(int i = 0; i < n; i++) {
//            for(int j = i + 1; j < n; j++) {
//                long sum = sumMatrix[i][j-1] + A[j];
//                if(sum == 0) {
//                    count++;
//                    if(count > 1000000000) return -1;
//                }
//                sumMatrix[i][j] = sum;
//            }
//        }
//
//        return count;
//    }

    public int solution(int[] A) {
        int count = 0;
        int sum = 0;
        Map<Integer, Integer> map = new HashMap();
        map.put(0, 0);
        for(int i = 0; i < A.length; i++) {
            sum += A[i];
            if(map.containsKey(sum)) {
                map.put(sum, map.get(sum) + 1);
            } else {
                map.put(sum, 0);
            }
        }
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            count += entry.getValue();
        }
        for(int i = 0; i < A.length; i++) {
            if(A[i] == 0) {
                count += 1;
                if(count > 100000000) return -1;
            }
        }
        return count;
    }
}
