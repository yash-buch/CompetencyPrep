package utilities.datastructures;

import utilities.exceptions.QueueOverFlowException;

import java.lang.reflect.Array;

public class BincArrayQueue<T> {
    private int capacity;
    private T[] queue;
    private int startIdx;
    private int endIdx;
    int size = 0;

    public BincArrayQueue(Class<T> type, int capacity) {
        this.capacity = capacity;
        queue = (T[]) Array.newInstance(type, capacity);
    }

    public void enqueque(T element) throws QueueOverFlowException {
        if (endIdx == startIdx && size != 0) {
            throw new QueueOverFlowException(capacity, size());
        }
        queue[endIdx] = element;
        size++;
        endIdx = incrementIndex(endIdx);
    }

    public T dequeue() throws QueueOverFlowException {
        if (isEmpty())
            throw new QueueOverFlowException(capacity, size());
        T element = queue[startIdx];
        startIdx = incrementIndex(startIdx);
        size--;
        return element;
    }

    public T peek() {
        return queue[startIdx];
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        int len;
        int diff = endIdx - startIdx;
        if (diff < 0) {
            len = capacity - diff;
        } else if (diff == 0) {
            len = size;
        } else {
            len = diff;
        }
        return len;
    }

    private int incrementIndex(int idx) {
        return (idx + 1) % capacity;
    }

    private int decrementIndex(int idx) {
        int index = idx - 1;
        if (index < 0) {
            index = capacity + index;
        }
        return index;
    }
}
