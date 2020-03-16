package problems.ctci;

public class MajorityElement {

    public int majorityElement(int[] arr) {
        int m = 0;
        int i = 0;
        for(int x : arr) {
            System.out.println(""+i);
            if(i == 0) {
                m = x;
                i = 1;
            } else if(m == x) {
                i = i+1;
            } else {
                i = i-1;
            }
        }
        return m;
    }

    public static void main(String[] args) {
        MajorityElement obj = new MajorityElement();
        int[] arr = new int[]{5,5,3,3,5,2,2};
        System.out.print(obj.majorityElement(arr)+"");
    }
}
