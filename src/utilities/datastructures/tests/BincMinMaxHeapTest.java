package utilities.datastructures.tests;

import org.junit.Test;
import utilities.datastructures.BincMinMaxHeap;
import utilities.exceptions.HeapOverFlowException;

public class BincMinMaxHeapTest {
    @Test
    public void test_insert() {
        BincMinMaxHeap<Integer, Integer> heap = new BincMinMaxHeap<Integer, Integer>(Integer.class, Integer::compareTo, 2);
        try {
            assert (heap.size() == 0);
            heap.insert(3);
            assert (heap.size() == 1);
        } catch (HeapOverFlowException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_peek() {
        BincMinMaxHeap<Integer, Integer> heap = new BincMinMaxHeap<Integer, Integer>(Integer.class, Integer::compareTo, 2);
        try {
            assert (heap.peek() == null);
            heap.insert(3);
            assert (heap.peek() == 3);
        } catch (HeapOverFlowException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_remove() {
        BincMinMaxHeap<Integer, Integer> heap = new BincMinMaxHeap<Integer, Integer>(Integer.class, Integer::compareTo, 2);
        try {
            assert (heap.size() == 0);
            heap.insert(3);
            assert (heap.size() == 1);
            int top = heap.remove();
            assert (top == 3);
            assert (heap.size() == 0);
        } catch (HeapOverFlowException e) {
            e.printStackTrace();
        }
    }

    @Test(expected = HeapOverFlowException.class)
    public void test_insertException() throws HeapOverFlowException {
        BincMinMaxHeap<Integer, Integer> heap = new BincMinMaxHeap<Integer, Integer>(Integer.class, Integer::compareTo, 1);
            heap.insert(3);
            heap.insert(2);
    }

    @Test(expected = HeapOverFlowException.class)
    public void test_removeException() throws HeapOverFlowException {
        BincMinMaxHeap<Integer, Integer> heap = new BincMinMaxHeap<Integer, Integer>(Integer.class, Integer::compareTo, 1);
        heap.remove();
    }
}
