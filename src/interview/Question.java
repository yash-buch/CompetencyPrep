package interview;

import org.junit.Test;

import java.util.Arrays;

public class Question {
    public void sort(int[] ar1, int[] ar2) {
        int n = ar1.length;
        int m = ar2.length;
        int[] aux = new int[n+m];
        int index = 0;
        for (int i = 0; i < n; i++) {
            aux[index++] = ar1[i];
        }
        for (int i = 0; i < m; i++) {
            aux[index++] = ar2[i];
        }
        Arrays.sort(aux);
        index = 0;
        for (int i = 0; i < n; i++) {
            ar1[i] = aux[index++];
        }
        for (int i = 0; i < m; i++) {
            ar2[i] = aux[index++];
        }
    }

    public void sortII(int[] ar1, int[] ar2) {
        int n = ar1.length;
        int m = ar2.length;
        int[] aux = new int[n+m];
        int index = 0;
        int i = 0, j = 0;
        while(i < n && j < m) {
            if (ar1[i] <= ar2[j]) {
                aux[index++] = ar1[i++];
            } else {
                aux[index++] = ar2[j++];
            }
        }
        while (i < n) {
            aux[index++] = ar1[i++];
        }
        while (j < m) {
            aux[index++] = ar2[j++];
        }
        index = 0;
        for (i = 0; i < n; i++) {
            ar1[i] = aux[index++];
        }
        for (i = 0; i < m; i++) {
            ar2[i] = aux[index++];
        }
    }

    @Test
    public void testMerge() {
        int[] ar1 = new int[]{1, 3, 5, 7};
        int[] ar2 = new int[]{0,2,6,8,9};
        Question obj = new Question();
        obj.sortII(ar1, ar2);
        System.out.println(Arrays.toString(ar1));
        System.out.println(Arrays.toString(ar2));
    }
}


/*
[3,5,7]
[0,2,6,8,9]


Input:
n = 4, arr1[] = [1 3 5 7]
m = 5, arr2[] = [0 2 6 8 9]

Output:
arr1[] = [0 1 2 3]
arr2[] = [5 6 7 8 9]



[0,1,3,7]
[5,2,6,8,9]


 */
