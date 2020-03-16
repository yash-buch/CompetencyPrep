package problems.ctci;

public class CallInOrder {

    public static void main(String[] args) {
        CallInOrder obj = new CallInOrder();
        Foo foo = obj.new Foo();
        Thread t1 = new Thread(obj.new FooRunnable(1, foo));
        Thread t2 = new Thread(obj.new FooRunnable(2, foo));
        Thread t3 = new Thread(obj.new FooRunnable(3, foo));
        t2.start();
        t1.start();
        t3.start();
    }


    class FooRunnable implements Runnable {
        private int id;
        private Foo foo;

        FooRunnable(int id, Foo foo) {
            this.id = id;
            this.foo = foo;
        }

        @Override
        public void run() {
            System.out.println(id + " started");
            switch (id) {
                case 1:
                    foo.first();
                    break;
                case 2:
                    foo.second();
                    break;
                case 3:
                    foo.third();
                    break;
            }
        }
    }

    class Foo {
        Object lockFirst = new Object();
        Object lockSecond = new Object();
        boolean isFirstExecuted = false;
        boolean isSecondExecuted = false;


        public void first() {
            System.out.println("first");
            isFirstExecuted = true;
            synchronized (lockFirst) {
                lockFirst.notify();
            }
        }

        public void second() {
            synchronized (lockFirst) {
                try {
                    if (!isFirstExecuted)
                        lockFirst.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("second");
                isSecondExecuted = true;
                synchronized (lockSecond) {
                    lockSecond.notify();
                }
            }
        }

        public void third() {
            try {
                synchronized (lockSecond) {
                    if (!isSecondExecuted)
                        lockSecond.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("third");
        }
    }
}
