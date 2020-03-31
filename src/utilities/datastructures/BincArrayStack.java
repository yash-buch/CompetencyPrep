package utilities.datastructures;

import java.lang.reflect.Array;

public class BincArrayStack<T> {
    T[] stack;
    int capacity;
    int index = -1;

    public BincArrayStack(Class<T> klass, int capacity) {
        this.capacity = capacity;
        stack = (T[]) Array.newInstance(klass, this.capacity);
    }

    public void push(T element) {
        if(index + 1 >= capacity) {
            throw new StackOverflowError();
        }
        stack[++index] = element;
    }

    public T pop() {
        if(isEmpty()) {
            throw new StackOverflowError();
        }
        return stack[index--];
    }

    public boolean isEmpty() {
        return index == -1;
    }

    public int size() {
        return index+1;
    }

    public T get(int idx) {
        if(idx < 0 || idx >= capacity) {
            throw new StackOverflowError();
        }
        return stack[idx];
    }
}
