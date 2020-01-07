package utilities.algorithms;

public class BincSort {
    //TODO: make it generic

    private static final int RUN = 2;

    public BincSort(){}
    private void insertionSort(int left, int right, int[] in) {
        int i = left+1;
        for(; i < right; i++) {
            int temp = in[i];
            for (int j = i - 1; j >= left; j--) {
                if (temp < in[j]) {
                    shift(j, in);
                    in[j] = temp;
                } else {
                    in[j + 1] = temp;
                    break;
                }
            }
        }
    }

    private void merge(int[] in, int l, int m, int r){
        int[] left = new int[m-l];
        int[] right = new int[r-m];
        for(int i = 0; i < left.length; i++) {
            left[i] = in[l+i];
        }
        for(int i = 0; i < right.length; i++) {
            right[i] = in[m+i];
        }

        int i = 0;
        int j = 0;
        int index = l;
        while(i < m-l && j < r-m) {
            if(left[i] <= right[j]) {
                in[index] = left[i];
                i++;
            } else {
                in[index] = right[j];
                j++;
            }
            index++;
        }
        while(i < m-l) {
            in[index] = left[i];
            i++;
            index++;
        }
        while(j < r-m) {
            in[index] = right[j];
            j++;
            index++;
        }
    }

    public void sort(int[] in){
        int n = in.length;
        for(int i = 0; i < n; i += RUN) {
            insertionSort(i, Math.min(i+RUN, n), in);
        }

        for(int size = RUN; size < n; size *= 2) {
            for(int left = 0; left < n; left += 2*size) {
                int right = Math.min(left+2*size, n);
                int mid = Math.min(right, left+size);
                //System.out.println("left:"+left+" mid:"+mid+" right:"+right);

                merge(in, left, mid, right);
            }
        }
    }

    private void shift(int index, int[] in) {
        if(index+1 < in.length) {
            in[index+1] = in[index];
        }
    }

    private void printArray(int[] in) {
        if(in.length == 0) return;
        System.out.print(in[0]);
        for(int i = 1; i < in.length; i++) {
            System.out.print(" "+in[i]);
        }
    }


    public static void main(String[] args) {
        int[] ar = new int[]{2,4,5,6,3,7,8,5,6,9,4,3,5,6,7,1,8,5,23,56,87,9,34,234};
        BincSort bs = new BincSort();
        bs.sort(ar);
        bs.printArray(ar);
    }
}
