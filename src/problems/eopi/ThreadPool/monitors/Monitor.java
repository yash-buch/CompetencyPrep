package problems.eopi.ThreadPool.monitors;

public interface Monitor {
    default void waitWithMessage(String msg) throws InterruptedException {
        System.out.println(msg);
        wait();
    }

    default void notifyWithMessage(String msg) {
        System.out.println(msg);
        notify();
    }
}
