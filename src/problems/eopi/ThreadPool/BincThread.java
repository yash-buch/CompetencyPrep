package problems.eopi.ThreadPool;

import problems.eopi.ThreadPool.beans.Task;
import problems.eopi.ThreadPool.beans.ThreadResult;
import problems.eopi.ThreadPool.monitors.Monitor;

public class BincThread extends Thread implements Monitor {
    Monitor monitor = this;
    Task task;
    ThreadResultObserver observer;
    boolean shutdown = false;
    int id;

    public BincThread(int id, ThreadResultObserver observer) {
        this.id = id;
        this.observer = observer;
    }

    @Override
    public void run() {
        while (true) {
            //wait for task
            waitForTask();
            // finish thread if shutdown = true
            if (shutdown)
                break;
            //perform task
            performTask();
            //submit result
            observer.setResult(new ThreadResult(id, task.result));
        }
    }

    private void performTask() {
        System.out.println("thread:"+id+" performing task");
        task.execute();
        //reset task
        task = null;
    }

    private synchronized void waitForTask() {
        if (task == null) {
            try {
                monitor.waitWithMessage("Waiting for task | id:"+id);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void addTask(Task task) {
        this.task = task;
        monitor.notify();
    }

    public synchronized void shutdown() {
        shutdown = true;
        monitor.notify();
    }

    interface ThreadResultObserver {
        void setResult(ThreadResult result);
    }
}
