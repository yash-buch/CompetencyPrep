package utilities.datastructures.tests;

import org.junit.Test;
import utilities.datastructures.BincArrayQueue;
import utilities.exceptions.QueueOverFlowException;

public class BincArrayQueueTest {
    @Test
    public void test_enqueue() {
        BincArrayQueue<Integer> queue = new BincArrayQueue<>(Integer.class, 3);
        try {
            queue.enqueque(1);
            assert (1 == queue.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_dequeue() {
        BincArrayQueue<Integer> queue = new BincArrayQueue<>(Integer.class, 3);
        try {
            queue.enqueque(1);
            int element = queue.dequeue();
            assert (element == 1);

        } catch (QueueOverFlowException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_peek() {
        BincArrayQueue<Integer> queue = new BincArrayQueue<>(Integer.class, 3);
        try {
            queue.enqueque(1);
            int element = queue.peek();
            assert (element == 1);
            assert (queue.size() == 1);
        } catch (QueueOverFlowException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_isEmpty() {
        BincArrayQueue<Integer> queue = new BincArrayQueue<>(Integer.class, 3);
        assert (queue.isEmpty());
        try {
            queue.enqueque(1);
            assert (!queue.isEmpty());
            queue.dequeue();
            assert (queue.isEmpty());
        } catch (QueueOverFlowException e) {
            e.printStackTrace();
        }
    }
}
