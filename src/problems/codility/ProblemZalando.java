package problems.codility;

import java.util.Scanner;

public class ProblemZalando {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String result = new ProblemZalando().solution(n);
        System.out.print(result);
    }

    public String solution(int N) {
        // write your code in Java SE 8
        StringBuffer sb = new StringBuffer("");
        for(int i = 0; i < N; i++) {
            if(i%2 == 0) {
                sb.append("+");
            } else {
                sb.append("-");
            }
        }
        return sb.toString();
    }
}
