package problems.ctci;

class ThreeStackOneArray {
    private int[] stack;
    private int s1;
    private int s2;
    private int s3;
    private int i1;
    private int i2 = 1;
    private int i3 = 2;
    public static final int stackOne = 0;
    public static final int stackTwo = 1;
    public static final int stackThree = 2;

    public ThreeStackOneArray(int n) {
        stack = new int[n];
    }

    public void push(int stackId, int element) throws Exception {
        switch (stackId) {
            case stackOne: {
                if (i1 >= stack.length) throw new Exception("");
                stack[i1] = element;
                i1 += 3;
                s1++;
                break;
            }
            case stackTwo: {
                if (i2 >= stack.length) throw new Exception("");
                stack[i2] = element;
                i2 += 3;
                s2++;
                break;
            }
            case stackThree: {
                if (i3 >= stack.length) throw new Exception("");
                stack[i3] = element;
                i3 += 3;
                s3++;
                break;
            }
        }
    }

    public int pop(int stackId) throws Exception {
        int val = 0;
        switch (stackId) {
            case stackOne: {
                if (i1 <= 0) throw new Exception("");
                i1 -= 3;
                val = stack[i1];
                s1--;
                break;
            }
            case stackTwo: {
                if (i2 <= 1) throw new Exception("");
                i2 -= 3;
                val = stack[i2];
                s2--;
                break;
            }
            case stackThree: {
                if (i3 <= 2) throw new Exception("");
                i3 -= 3;
                val = stack[i3];
                s3--;
                break;
            }
        }
        return val;
    }

    public int size(int stackId) {
        switch (stackId) {
            case stackOne:
                return s1;
            case stackTwo:
                return s2;
            case stackThree:
                return s3;
        }
        return -1;
    }

    public boolean isEmpty(int stackId) {
        switch (stackId) {
            case stackOne:
                return s1 == 0;
            case stackTwo:
                return s2 == 0;
            case stackThree:
                return s3 == 0;
        }
        return true;
    }

    public static void main(String[] args) {
        ThreeStackOneArray obj = new ThreeStackOneArray(9);
        try {
            obj.push(ThreeStackOneArray.stackOne, 2);
            obj.push(ThreeStackOneArray.stackTwo, 1);
            obj.push(ThreeStackOneArray.stackThree, 2);

            System.out.println("" + (obj.size(ThreeStackOneArray.stackOne) == 1));
            System.out.println("" + (obj.pop(ThreeStackOneArray.stackOne) == 2));
            System.out.println("" + (obj.size(ThreeStackOneArray.stackOne) == 0));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
