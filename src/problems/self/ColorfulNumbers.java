package problems.self;

import java.util.ArrayList;
import java.util.List;

public class ColorfulNumbers {
    List<Integer> colors = new ArrayList<>();
    static final int RED = 0;
    static final int GREEN = 1;
    static final int BLUE = 2;
    int i = 0;

    public static void main(String[] args) {
        ColorfulNumbers obj = new ColorfulNumbers();
        obj.colors.add(RED);
        obj.colors.add(GREEN);
        obj.colors.add(GREEN);
        obj.colors.add(GREEN);
        obj.colors.add(RED);
        obj.colors.add(BLUE);
        obj.colors.add(RED);
        obj.colors.add(BLUE);
        obj.colors.add(RED);
        obj.colors.add(BLUE);
        Thread red = new Thread(() -> {
            while(obj.i < obj.colors.size()) {
                synchronized (obj) {
                    if (obj.i < obj.colors.size() && obj.colors.get(obj.i) == RED) {
                        synchronized (obj) {
                            System.out.print(", red:"+obj.colors.get(obj.i));
                            obj.i++;
                        }
                    } else {
                        obj.notify();
                    }
                }
            }
        });

        Thread green = new Thread(() -> {
            while(obj.i < obj.colors.size()) {
                synchronized (obj) {
                    if (obj.i < obj.colors.size() && obj.colors.get(obj.i) == GREEN) {
                        synchronized (obj) {
                            System.out.print(", green:"+obj.colors.get(obj.i));
                            obj.i++;
                        }
                    } else {
                        obj.notify();
                    }
                }
            }
        });

        Thread blue = new Thread(() -> {
            while(obj.i < obj.colors.size()) {
                synchronized (obj) {
                    if (obj.i < obj.colors.size() && obj.colors.get(obj.i) == BLUE) {
                        synchronized (obj) {
                            System.out.print(", blue:"+obj.colors.get(obj.i));
                            obj.i++;
                        }
                    } else {
                        obj.notify();
                    }
                }
            }
        });

        red.start();
        green.start();
        blue.start();
        try {
            red.join();
            green.join();
            blue.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
