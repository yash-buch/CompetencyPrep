package problems.googi;

import org.junit.Test;

import java.util.Arrays;

/*
[B, C, A, D] -take this to-> [C, D, A, B]
only swaps of adjacent elements are allowed.
return minimum swaps required to turn [B, C, A, D] to [C, D, A, B]
Answer in this case 4.
explanation:
swap [B,C] -result into-> [C, B, A, D]
swap [B,A] -result into-> [C, A, B, D]
swap [B,D] -result into-> [A, A, D, B]
swap [A,D] -result into-> [C, D, A, B]

Below is n^2 solution.
Can be solved in nlogn also.
 */
public class GI24AUG21 {
    public int modify(char[] one, char[] two) {
        int res = 0;
        for (int i = two.length-1; i >= 0; i--) {
            char c = two[i];
            //find its location in one
            int loc = find(one, c, i);
            //compute the distance
            res += i-loc;
        }
        return res;
    }

    private int find(char[] one, char c, int len) {
        int loc = -1;
        for (int i = 0; i <= len; i++) {
            if (c == one[i])
                loc = i;
            if (loc != -1) {
                if (i+1 > len) {
                    one[i] = '_';
                } else
                    one[i] = one[i+1];
            }
        }
        one[len] = c;
        return loc;
    }

    @Test
    public void test() {
        GI24AUG21 obj = new GI24AUG21();
        char[] one = new char[]{'B', 'C', 'A', 'D'};
        char[] two = new char[]{'C', 'D', 'B', 'A'};
        int res = obj.modify(one, two);
        System.out.println(res);
        System.out.println(Arrays.toString(one));
        System.out.println(Arrays.toString(two));
        assert 3 == res;
    }
}
