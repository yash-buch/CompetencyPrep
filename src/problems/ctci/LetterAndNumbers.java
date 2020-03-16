// Q: Given an array filled with letters and numbers, find the longest subarray with an equal number of letters and numbers.
package problems.ctci;

public class LetterAndNumbers {
    public static void main(String[] args) {
        char[] in = new char[]{'1','a','2', '3', 'b'};
        LetterAndNumbers obj = new LetterAndNumbers();
        obj.evaluate(in);
    }

    void evaluate(char[] in) {
        int max = -1;
        int n = in.length-1;
        int subArrI = 0;
        int subArrJ = 0;
        int numbers = 0;
        int letters = 0;
        int[][][] numLetters = new int[n+1][n+1][2];
        for(int i = 0; i <= n; i++) {
            if(isNumber(in[i])) {
                numbers++;
            } else {
                letters++;
            }
        }

        numLetters[0][n][0] = numbers; //<======
        numLetters[0][n][1] = letters; //<======

        numLetters[0][0][0] = isNumber(in[0]) ? 1 : 0;
        numLetters[0][0][1] = isNumber(in[0]) ? 0 : 1;
        numLetters[1][n][0] = isNumber(in[1]) ? (numbers > 0 ? numbers - 1 : 0) : 0;
        numLetters[1][n][1] = isNumber(in[1]) ? 0: (letters > 0 ? letters - 1 : 0);

        numLetters[n-1][n][0] = isNumber(in[n]) ? 1 : 0;
        numLetters[n-1][n][1] = isNumber(in[n]) ? 0 : 1;
        numLetters[0][n-1][0] = isNumber(in[n]) ? (numbers > 0 ? numbers - 1 : 0) : 0;
        numLetters[1][n][1] = isNumber(in[n]) ? 0: (letters > 0 ? letters - 1 : 0);

        for(int i = 1; i < n; i++) {
            boolean isNumber = isNumber(in[i]);
            if(isNumber) {
                //[0,i]
                numLetters[0][i][0] =  numLetters[0][i-1][0] + 1;
                numLetters[0][i][1] = numLetters[0][i-1][1];
                //(i, n]
                numLetters[i+1][n][0] = (numLetters[i-1][n][0] - 1 < 0) ? 0 : numLetters[i-1][n][0] - 1;
                numLetters[i+1][n][1] = numLetters[i-1][n][1];
            } else {
                //[0,i)
                numLetters[0][i][0] =  numLetters[0][i-1][0];
                numLetters[0][i][1] = numLetters[0][i-1][1] + 1;
                //(i, n)
                numLetters[i+1][n][0] = numLetters[i-1][n][0];
                numLetters[i+1][n][1] = (numLetters[i-1][n][1] - 1 < 0) ? 0 : numLetters[i-1][n][1] - 1;
            }
        }

        for(int i = 1; i < n; i++) {
            for(int j = i; j < n; j++) {
                numLetters[i][j][0] = numbers - numLetters[0][i-1][0] - numLetters[j+1][n][0];
                numLetters[i][j][1] = numbers - numLetters[0][i-1][1] - numLetters[j+1][n][1];
            }
        }

        for(int i = 1; i < n; i++) {
            for (int j = i; j < n; j++) {
                if(numLetters[i][j][0] == numLetters[i][j][1]) {
                    if(max < numLetters[i][j][0]) {
                        max = numLetters[i][j][0];
                        subArrI = i;
                        subArrJ = j;
                    }
                }
            }
        }

        System.out.println("subArrI:"+subArrI+",subArrJ:"+subArrJ);
    }

    private boolean isNumber(char c) {
        return 48 <= (int)c && (int)c <= 57;
    }
}
