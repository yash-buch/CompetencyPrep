package problems.eopi;

import org.junit.Test;
import utilities.datastructures.BincMinMaxHeap;
import utilities.exceptions.HeapOverFlowException;

import java.util.Comparator;

public class ComputeFairBonus {

    public int computeFairBonus(int[] perfMat) {
        if (perfMat == null) return 0;
        int n = perfMat.length;
        if (n == 0) return 0;
        int bonus = 0;
        int[] aux = new int[n];
        BincMinMaxHeap<Integer, Integer> heap =
                new BincMinMaxHeap<>(Integer.class, new CodeComparator(perfMat), n);
        try {
            for (int i = 0; i < n; i++) {
                pushToHeap(i, perfMat, aux, heap);
            }
            popFromHeap(heap, aux);
            for (int i = 0; i < aux.length; i++) {
                bonus += aux[i];
            }
        } catch (HeapOverFlowException e) {
            e.printStackTrace();
        }

        return bonus;
    }

    private void pushToHeap(int idx, int[] perfMat, int[] aux, BincMinMaxHeap<Integer, Integer> heap)
            throws HeapOverFlowException {
        if (heap.size() == 0 || perfMat[idx] < perfMat[heap.peek()]) {
            heap.insert(idx);
        } else {
            //initiate pop sequence
            popFromHeap(heap, aux);
            //insert index
            heap.insert(idx);
        }
    }

    private void popFromHeap(BincMinMaxHeap<Integer, Integer> heap, int[] aux) throws HeapOverFlowException {
        int bonus = 1;
        while(heap.size() > 1) {
            int idx = heap.remove();
            aux[idx] += bonus++;
        }
        int idx = heap.remove();
        if (idx == 0) aux[idx] = bonus;
        else {
            aux[idx] = Math.max(aux[idx - 1], bonus-1) + 1;
        }
    }

    class CodeComparator implements Comparator<Integer> {
        int[] perMat;

        CodeComparator(int[] ar) {
            this.perMat = ar;
        }

        @Override
        public int compare(Integer o1, Integer o2) {
            if (perMat != null) {
                if (perMat[o1] > perMat[o2]) {
                    return 1;
                } else if (perMat[o1] < perMat[o2]) {
                    return -1;
                }
            }

            return 0;
        }
    }

    @Test
    public void test_computeFairBonus() {
        ComputeFairBonus obj = new ComputeFairBonus();
        int[] perfMat = new int[]{3, 2, 1, 2, 3};
        int result = obj.computeFairBonus(perfMat);
        System.out.println(result);
        assert 11 == result;
    }
}
