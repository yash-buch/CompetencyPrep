package utilities.algorithms;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BincPermutation<T> {
    List<T[]> result = new ArrayList<>();

    public List<T[]> permute(T[] ar) {
        permute(ar, 0);
        return result;
    }

    private void permute(T[] arr, int l) {
        if(l == arr.length-1) {
            result.add(Arrays.copyOf(arr, arr.length));
            return;
        }
        for(int i = l ; i < arr.length; i++) {
            swap(arr, l, i);
            permute(arr, l+1);
            swap(arr, l, i);
        }
    }

    private void swap(T[] arr, int a, int b) {
        T temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String[] args) {
        BincPermutation<Integer> bp = new BincPermutation<>();
        Integer[] ar = new Integer[]{1,2,3};
        List<Integer[]> list = bp.permute(ar);
        for(Integer[] arr : list) {
            for(Integer i : arr) {
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }
}
