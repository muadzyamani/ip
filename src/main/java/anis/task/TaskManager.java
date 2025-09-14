package anis.task;

import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks;

    public TaskManager(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public boolean isInvalidTaskNumber(int taskNumber) {
        return taskNumber <= 0 || taskNumber > tasks.size();
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }

    public int getTaskCount() {
        return tasks.size();
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
