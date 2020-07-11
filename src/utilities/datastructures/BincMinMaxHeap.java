package utilities.datastructures;

import utilities.exceptions.HeapOverFlowException;

import java.lang.reflect.Array;
import java.util.Comparator;

public class BincMinMaxHeap<T, K> {
    private Comparator comparator;
    private T[] heap;
    private int capacity;
    private int size;

    public BincMinMaxHeap(Class<T> klass, Comparator<K> comparator, int capacity) {
        this.comparator = comparator;
        this.capacity = capacity;
        heap = (T[]) Array.newInstance(klass, capacity);
    }

    public T peek() {
        return heap[0];
    }

    public void insert(T element) throws HeapOverFlowException {
        if (size + 1 >= capacity) {
            throw new HeapOverFlowException();
        }
        heap[size++] = element;
        int index = size-1;
        while(index > 0) {
            int parent = (int) Math.floor((double)(index-1)/2);
            if(comparator.compare(heap[index], heap[parent]) == -1) {
                swap(index, parent);
                index = parent;
            } else {
                break;
            }
        }
    }

    public T remove() throws HeapOverFlowException {
        if (size == 0) {
            throw new HeapOverFlowException();
        }
        T top = heap[0];
        swap(0, --size);
        int index = 0;
        while(index < size) {
            int minIdx = heapify(index);
            if (index == minIdx) break;
            index = minIdx;
        }
        return top;
    }

    public int size() {
        return size;
    }

    private int heapify(int index) {
        int minIdx = index;
        int left = index*2 + 1;
        int right = index*2 + 2;
        if(left < size && comparator.compare(heap[left], heap[minIdx]) == -1) {
            minIdx = left;
        }
        if(right < size && comparator.compare(heap[right], heap[minIdx]) == -1) {
            minIdx = right;
        }
        //swap index and minIdx
        swap(index, minIdx);
        return minIdx;
    }

    private void swap(int a, int b) {
        T temp = heap[a];
        heap[a] = heap[b];
        heap[b] = temp;
    }
}
