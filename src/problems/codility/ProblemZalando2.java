package problems.codility;

import java.util.Scanner;

public class ProblemZalando2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = -12645;
        int result = new ProblemZalando2().solution(n);
        System.out.print(result);
    }

    public int solution(int N) {
        // write your code in Java SE 8
        String num = String.valueOf(N);
        //System.out.println(num);
        char[] charArr = num.toCharArray();
        if (charArr[0] == '-') {
            return processNegative(charArr);
        } else {
            return processPositive(charArr);
        }
    }

    private int processNegative(char[] chArr) {
        int index = 1;
        for (int i = 1; i < chArr.length; i++) {
            int c = chArr[i] - 48;
            if (c > 5) {
                break;
            }
            index++;
        }
        String numAsString = generateNumber(chArr, index);
        return Integer.valueOf(numAsString);
    }

    private int processPositive(char[] chArr) {
        int index = 0;
        for (int i = 0; i < chArr.length; i++) {
            int c = chArr[i] - 48;
            if (c < 5) {
                break;
            }
            index++;
        }
        String numAsString = generateNumber(chArr, index);
        return Integer.valueOf(numAsString);
    }

    private String generateNumber(char[] chArr, int index) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chArr.length; i++) {
            if (i == index) {
                sb.append("5");
            }
            sb.append(chArr[i]);
        }
        if(index == chArr.length) {
            sb.append("5");
        }
        return sb.toString();
    }
}
