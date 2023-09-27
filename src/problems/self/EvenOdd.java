package problems.self;

public class EvenOdd {
    int shared = 1;

    public static void main(String[] args) {
        EvenOdd obj = new EvenOdd();
        Thread odd = new Thread(() -> {
            while(obj.shared < 101) {
                synchronized (obj) {
                    if (obj.shared < 101 && obj.shared % 2 == 1) {
                        synchronized (obj) {
                            System.out.print(obj.shared+",");
                            obj.shared++;
                        }
                    } else {
                        obj.notify();
                    }
                }
            }
        });

        Thread even = new Thread(() -> {
            while(obj.shared < 101) {
                synchronized (obj) {
                    if (obj.shared < 101 && obj.shared % 2 == 0) {
                        synchronized (obj) {
                            System.out.print(obj.shared+",");
                            obj.shared++;
                        }
                    } else {
                        obj.notify();
                    }
                }
            }
        });

        even.start();
        odd.start();
        try {
            even.join();
            odd.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
/*
Feedback: Yash needs more speed.
Interval trees, PQs, Graphs and traversals

 */