package problems.eopi.ThreadPool.tests;

import org.junit.Test;
import problems.eopi.ThreadPool.ThreadPoolManager;
import problems.eopi.ThreadPool.beans.Task;

public class ThreadPoolTest {

    @Test
    public void test_singleTask() {
        ThreadPoolManager threadPoolManager = new ThreadPoolManager(3, 10);
        Task task = () -> {
            int i = 3;
            while(i > 0) {
                System.out.println(i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i--;
            }
        };

        threadPoolManager.enqueue(task);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPoolManager.destroy();
    }

    @Test
    public void test_taskNumberGreaterThanPool() {
        ThreadPoolManager threadPoolManager = new ThreadPoolManager(1, 10);
        Task taskOne = () -> {
            int i = 3;
            while(i > 0) {
                System.out.println(i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i--;
            }
        };

        threadPoolManager.enqueue(taskOne);

        Task taskTwo = () -> {
            int i = 4;
            while(i < 8) {
                System.out.println(i);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        };

        threadPoolManager.enqueue(taskTwo);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadPoolManager.destroy();
    }

    @Test
    public void test_handleTaskParallely() {
        ThreadPoolManager threadPoolManager = new ThreadPoolManager(3, 10);
        Task taskOne = () -> {
            int i = 3;
            while(i > 0) {
                System.out.println(i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i--;
            }
        };

        threadPoolManager.enqueue(taskOne);

        Task taskTwo = () -> {
            int i = 4;
            while(i < 8) {
                System.out.println(i);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        };

        threadPoolManager.enqueue(taskTwo);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadPoolManager.destroy();
    }

    @Test
    public void test_miscellaneous() {
        ThreadPoolManager threadPoolManager = new ThreadPoolManager(2, 10);
        Task taskOne = () -> {
            int i = 3;
            while(i > 0) {
                System.out.println(i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i--;
            }
        };

        threadPoolManager.enqueue(taskOne);

        Task taskTwo = () -> {
            int i = 4;
            while(i < 8) {
                System.out.println(i);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        };

        threadPoolManager.enqueue(taskTwo);

        Task taskThree = () -> {
            int i = 9;
            while(i < 12) {
                System.out.println(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        };

        threadPoolManager.enqueue(taskThree);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        threadPoolManager.destroy();
    }
}
