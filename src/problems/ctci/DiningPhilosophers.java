package problems.ctci;

import static java.lang.Thread.sleep;

public class DiningPhilosophers {

    class Philosopher implements Runnable {
        ChopStick left, right;

        Philosopher(ChopStick left, ChopStick right) {
            this.left = left;
            this.right = right;
        }

        boolean haveEaten = false;
        @Override
        public void run() {
            while(!haveEaten) {
                synchronized (left) {
                    left.setInUse(true);
                    if(!right.isInUse()) {
                        synchronized (right) {
                            right.setInUse(true);
                            try {
                                System.out.println(Thread.currentThread().getName()+":eating");
                                sleep(5);
                                haveEaten = true;
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            right.setInUse(false);
                        }
                    }
                    left.setInUse(false);
                }
            }
        }
    }



    class ChopStick {
        private boolean inUse = false;
        private int id = 0;

        ChopStick(int id) {
            this.id = id;
        }
        public boolean isInUse() {
            return inUse;
        }

        public void setInUse(boolean inUse) {
            if(inUse) System.out.println(id+" picked");
            else System.out.println(id+" dropped");
            this.inUse = inUse;
        }
    }

    public static void main(String[] args) {
        DiningPhilosophers obj = new DiningPhilosophers();
        DiningPhilosophers.ChopStick cs1 = obj.new ChopStick(1);
        DiningPhilosophers.ChopStick cs2 = obj.new ChopStick(2);
        DiningPhilosophers.ChopStick cs3 = obj.new ChopStick(3);
        DiningPhilosophers.ChopStick cs4 = obj.new ChopStick(4);
        DiningPhilosophers.ChopStick cs5 = obj.new ChopStick(5);
        Thread t1 = new Thread(obj.new Philosopher(cs1, cs2));
        Thread t2 = new Thread(obj.new Philosopher(cs2, cs3));
        Thread t3 = new Thread(obj.new Philosopher(cs3, cs4));
        Thread t4 = new Thread(obj.new Philosopher(cs4, cs5));
        Thread t5 = new Thread(obj.new Philosopher(cs5, cs1));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}
