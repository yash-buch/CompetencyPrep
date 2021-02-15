package problems.ctci;

import org.junit.Test;

import java.util.Stack;

public class OOneStack {
    Stack<Integer> stack = new Stack<>();
    Stack<Integer> auxStack = new Stack<>();
    int min = 0;

    public void push(int element) {
        if (stack.size() == 0 || element <= auxStack.peek()) {
            auxStack.push(element);
            min = element;
        }
        stack.push(element);
    }

    public int pop() {
        int popped = stack.pop();
        if (popped == min) {
            auxStack.pop();
            min = auxStack.peek();
        }
        return popped;
    }

    public int min() {
        return min;
    }

    @Test
    public void testOOneStack() {
        OOneStack obj = new OOneStack();
        obj.push(3);
        obj.push(2);
        assert 2 == obj.min();

        obj.push(1);
        assert 1 == obj.min();

        obj.pop();
        assert 2 == obj.min();

    }
}
