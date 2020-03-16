package utilities.algorithms;

public class BincDeadLock {

    public void play() {
        Object lockA = new Object();
        Object lockB = new Object();
        T1 t1 = new T1(lockA, lockB);
        T1 t2 = new T1(lockB, lockA);
        t1.start();
        t2.start();
    }

    class T1 extends Thread {
        private Object lockOne;
        private Object lockTwo;

        T1(Object lock1, Object lock2) {
            this.lockOne = lock1;
            this.lockTwo = lock2;
        }

        @Override
        public void run() {
                synchronized (lockOne) {
                    System.out.println(Thread.currentThread().getName()+":locked lockOne");
                    synchronized (lockTwo) {
                        System.out.println(Thread.currentThread().getName()+":locked lockTwo");
                    }
                }
        }
    }

    public static void main(String[] args) {
        BincDeadLock obj = new BincDeadLock();
        obj.play();
    }
}
