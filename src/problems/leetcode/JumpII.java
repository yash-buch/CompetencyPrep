package problems.leetcode;

public class JumpII {

    public int jump(int[] nums) {
        int n = nums.length;
        if(n <= 1) return 0;
        int[][] memo = new int[n][n];
        for(int i = 1; i <= nums[0]; i++) {
            memo[0][i] = 1;
        }
        for(int i = 1; i < n-1; i++) {
            int base = Integer.MAX_VALUE;
            int k = i;
            while(k > 0) {
                int temp = memo[k-1][i];
                if(temp!= 0 && temp < base) base = temp;
                k--;
            }
            for(int j = 1; j <= nums[i] && i+j < n; j++) {
                memo[i][i+j] = base + 1;
            }
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            if(memo[i][n-1]!=0 && min > memo[i][n-1])
                min = memo[i][n-1];
        }
        return min;
    }


    public static void main(String[] args) {
        JumpII obj = new JumpII();
        int[] ar = new int[]{1,2,3};
        int res = obj.jump(ar);
        System.out.print(res);
    }
}
