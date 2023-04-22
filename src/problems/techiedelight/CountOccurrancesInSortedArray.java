package problems.techiedelight;

import org.junit.Test;

public class CountOccurrancesInSortedArray {

    public int numOccurrences(int[] in, int target) {
        int result;
        double before = target-0.5;
        double after = target+0.5;
        int idxBefore = binSearch(in, before, 0, in.length-1, 0.5);
        int idxAfter = binSearch(in, after, 0, in.length-1, 0.5);
        int idxTarget = binSearch(in, target, 0, in.length-1, 0.0);
        if (idxTarget == -1) {
            result = -1;
        } else {
            result = idxAfter-idxBefore;
        }
        return result;
    }

    private int binSearch(int[] in, double target, int l, int r, double adj) {
        if (r < l) return -1;
        if (r == l) {
            if (in[l] == (target+adj)) return l;
            if (in[l] == (target-adj)) return l+1;
            return -1;
        }
        int mid = l+ (r-l)/2;
        if (in[mid] == target) {

            return mid;
        }
        if (in[mid] > target) {
            return binSearch(in, target, l, mid-1, adj);
        } else {
            return binSearch(in, target, mid+1, r, adj);
        }
    }

    @Test
    public void testBinSearch() {
        int in[] = new int[]{1,2,3,4,5,5,6,7,8};
        CountOccurrancesInSortedArray obj = new CountOccurrancesInSortedArray();

        int res = obj.binSearch(in, 2, 0, in.length-1, 0.0);
        assert res == 1;
    }

    @Test
    public void test_numOccurrences() {
        int[] in = new int[]{1,2,3,4,5,5,6,7,8};
        CountOccurrancesInSortedArray obj = new CountOccurrancesInSortedArray();

        int res = obj.numOccurrences(in, 5);
        assert res == 2;
    }
}
