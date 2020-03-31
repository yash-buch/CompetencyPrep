package utilities.exceptions;

public class QueueOverFlowException extends Exception {
    private int capacity;
    private int size;

    public QueueOverFlowException(int capacity, int size) {
        this.capacity = capacity;
        this.size = size;
    }

    @Override
    public String getMessage() {
        return "Queue overflowed. capacity:" + capacity + " size:" + size;
    }
}
