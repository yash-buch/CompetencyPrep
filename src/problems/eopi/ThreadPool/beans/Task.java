package problems.eopi.ThreadPool.beans;

public interface Task {
    TaskResult result = null;
    void execute();
}
