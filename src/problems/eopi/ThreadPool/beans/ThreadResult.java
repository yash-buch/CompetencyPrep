package problems.eopi.ThreadPool.beans;

public class ThreadResult {
    int executorId;
    TaskResult taskResult;

    public ThreadResult(int id, TaskResult result) {
        this.executorId = id;
        this.taskResult = result;
    }

    public int getExecutorId() {
        return executorId;
    }
}
