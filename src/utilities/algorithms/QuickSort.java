package utilities.algorithms;

import org.junit.Test;

public class QuickSort {

    public void sort(int[] arr) {
        quickSort(arr, 0, arr.length);
    }

    private void quickSort(int[] in, int l, int r) {
        int pivot = partition(in, l, r);
        if (pivot > l) quickSort(in, l, pivot);
        if (pivot+1 < r) quickSort(in, pivot+1,r);
    }

    private int partition(int[] in, int l, int r) {
        int s = in[l];
        int pos = l;
        for (int i = l+1; i < r; i++) {
            if (in[i] < s) {
                swap(i, ++pos, in);
            }
        }
        swap(pos, l, in);
        return pos;
    }

    private void swap(int a, int b, int[] ar) {
        int temp = ar[a];
        ar[a] = ar[b];
        ar[b] = temp;
    }

    @Test
    public void testQuickSort() {
        int[] actual = new int[]{9,7,6,5,4,3,2};

        QuickSort obj = new QuickSort();
        obj.sort(actual);

        int[] expected = new int[]{2,3,4,5,6,7,9};
        for(int i = 0; i < expected.length; i++) {
            System.out.print(actual[i]+",");
            assert expected[i] == actual[i];
        }
    }
}
