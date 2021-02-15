package utilities.algorithms;

import org.junit.Test;

public class BinarySearch {
    public int find(int[] ar, int target) {
        return bs(ar, target, 0, ar.length-1);
    }

    private int bs(int[] ar, int target, int l, int r) {
        int result;
        int mid = (l+r)/2;
        if (l == r)
            return target == ar[mid] ? mid : -1;
        if (ar[mid] == target) result = mid;
        else if (ar[mid] > target) {
            result = bs(ar, target, l, mid);
        } else {
            result = bs(ar, target, mid+1, r);
        }
        return result;
    }

    @Test
    public void testBinarySearch() {
        BinarySearch obj = new BinarySearch();
        int[] in = new int[]{1,2,3,4,5,6,7,8,9,44};
        int idx = obj.find(in, 0);
        assert -1 == idx;
    }
}
