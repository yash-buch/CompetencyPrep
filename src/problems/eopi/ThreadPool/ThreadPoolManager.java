package problems.eopi.ThreadPool;

import problems.eopi.ThreadPool.beans.Task;
import problems.eopi.ThreadPool.beans.ThreadResult;
import problems.eopi.ThreadPool.monitors.Monitor;
import utilities.datastructures.BincArrayQueue;
import utilities.exceptions.QueueOverFlowException;

import java.util.HashMap;
import java.util.Map;

public class ThreadPoolManager {
    private int poolSize;
    private ThreadDispatcher dispatcher;

    public ThreadPoolManager(int poolSize, int maxNumTasks) {
        this.poolSize = poolSize;
        dispatcher = new ThreadDispatcher(poolSize, maxNumTasks);
        dispatcher.start();
    }

    public void enqueue(Task task) {
        dispatcher.enqueueTask(task);
    }

    public void destroy() {
        dispatcher.shutdown();
    }

    class ThreadDispatcher extends Thread implements Monitor, BincThread.ThreadResultObserver {
        int numLiveThreads;
        BincArrayTaskQueueWithMonitor taskQueue;
        BincArrayThreadQueueWithMonitor freeThreadQueue;
        Map<Integer, BincThread> threadMap;
        final Monitor threadMonitor;
        final Monitor taskMonitor;
        private boolean shutdown = false;

        ThreadDispatcher(int poolSize, int maxTaskCount) {
            freeThreadQueue = new BincArrayThreadQueueWithMonitor(BincThread.class, poolSize);
            taskQueue = new BincArrayTaskQueueWithMonitor(Task.class, maxTaskCount);
            threadMap = new HashMap<>();
            threadMonitor = freeThreadQueue;
            taskMonitor = taskQueue;
        }


        @Override
        public void run() {
            while (true) {
                //wait for task
                waitForTask();
                if (shutdown) break;
                Task newTask = getNewTask();
                //wait for free thread
                waitForFreeThread();
                if (shutdown) break;
                BincThread freeThread = getFreeThread();
                //dispatch task
                assert freeThread != null;
                freeThread.addTask(newTask);
            }
        }

        private void waitForTask() {
            synchronized (taskMonitor) {
                if (taskQueue == null || taskQueue.isEmpty()) {
                    try {
                        taskMonitor.waitWithMessage("waiting for task | id:-1");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void enqueueTask(Task task) {
            synchronized (taskMonitor) {
                try {
                    taskQueue.enqueque(task);
                    taskMonitor.notifyWithMessage("Task added | id:-1");
                } catch (QueueOverFlowException e) {
                    e.printStackTrace();
                }
            }
        }

        public void shutdown() {
            synchronized (threadMonitor) {
                synchronized (taskMonitor) {
                    shutdown = true;
                    shutDownThreads();
                    threadMonitor.notify();
                    taskMonitor.notify();
                }
            }
        }

        private void shutDownThreads() {
            for (Map.Entry<Integer, BincThread> entry : threadMap.entrySet()) {
                entry.getValue().shutdown();
            }
        }

        private void waitForFreeThread() {
            synchronized (threadMonitor) {
                if (freeThreadQueue.isEmpty()) {
                    if (numLiveThreads < poolSize) {
                        try {
                            BincThread thread = new BincThread(++numLiveThreads, this);
                            thread.start();
                            threadMap.put(numLiveThreads, thread);
                            freeThreadQueue.enqueque(threadMap.get(numLiveThreads));
                        } catch (QueueOverFlowException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            threadMonitor.waitWithMessage("Waiting for free thread | id:-1");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        private BincThread getFreeThread() {
            synchronized (threadMonitor) {
                try {
                    return freeThreadQueue.dequeue();
                } catch (QueueOverFlowException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

        private Task getNewTask() {
            synchronized (taskMonitor) {
                try {
                    return taskQueue.dequeue();
                } catch (QueueOverFlowException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }

        private void addFreeThread(int id) {
            synchronized (threadMonitor) {
                try {
                    freeThreadQueue.enqueque(threadMap.get(id));
                    threadMonitor.notify();
                } catch (QueueOverFlowException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void setResult(ThreadResult result) {
            addFreeThread(result.getExecutorId());
        }
    }

    class BincArrayThreadQueueWithMonitor extends BincArrayQueue<BincThread> implements Monitor {

        BincArrayThreadQueueWithMonitor(Class<BincThread> type, int capacity) {
            super(type, capacity);
        }
    }

    class BincArrayTaskQueueWithMonitor extends BincArrayQueue<Task> implements Monitor {

        BincArrayTaskQueueWithMonitor(Class<Task> type, int capacity) {
            super(type, capacity);
        }
    }
}
