//https://leetcode.com/problems/median-of-two-sorted-arrays/
package problems.leetcode;

public class ProblemMedianOfTwoSortedArrays {
    public static void main(String[] args) {
        int[] nums1 = new int[]{1, 2};
        int[] nums2 = new int[]{3, 4};
        ProblemMedianOfTwoSortedArrays obj = new ProblemMedianOfTwoSortedArrays();
        System.out.println(obj.findMedianSortedArrays(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int[] in = merge(nums1, nums2);
        double median;
        if(in.length%2 != 0)
            median = in[in.length/2];
        else
            median = (double)(in[(in.length-1)/2] + in[(in.length-1)/2+1])/2;
        return median;
    }

    private int[] merge(int[] in1, int[] in2){

        int i = 0;
        int j = 0;
        int index = 0;
        int[] in = new int[in1.length+in2.length];
        while(i < in1.length && j < in2.length) {
            if(in1[i] <= in2[j]) {
                in[index] = in1[i];
                i++;
            } else {
                in[index] = in2[j];
                j++;
            }
            index++;
        }
        while(i < in1.length) {
            in[index] = in1[i];
            i++;
            index++;
        }
        while(j < in2.length) {
            in[index] = in2[j];
            j++;
            index++;
        }
        return in;
    }
}
