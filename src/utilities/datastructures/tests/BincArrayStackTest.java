package utilities.datastructures.tests;

import org.junit.Test;
import utilities.datastructures.BincArrayStack;

public class BincArrayStackTest {

    @Test
    public void test_push() {
        BincArrayStack<Integer> stack = new BincArrayStack<>(Integer.class, 3);
        stack.push(2);
        assert (stack.size() == 1);
        assert (stack.get(0) == 2);
    }

    @Test
    public void test_isEmpty() {
        BincArrayStack<Integer> stack = new BincArrayStack<>(Integer.class, 3);
        assert (stack.isEmpty());
        stack.push(2);
        assert (!stack.isEmpty());
    }

    @Test
    public void test_pop() {
        BincArrayStack<Integer> stack = new BincArrayStack<>(Integer.class, 3);
        stack.push(4);
        assert (stack.pop() == 4);
        assert (stack.size() == 0);
    }

    @Test(expected = StackOverflowError.class)
    public void test_exceptionOnPush() {
        BincArrayStack<Integer> stack = new BincArrayStack<>(Integer.class, 1);
        stack.push(1);
        stack.push(2);

    }

    @Test(expected = StackOverflowError.class)
    public void test_exceptionOnPop() {
        BincArrayStack<Integer> stack = new BincArrayStack<>(Integer.class, 1);
        stack.pop();

    }
}
