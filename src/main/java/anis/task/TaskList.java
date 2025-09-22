package anis.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber - 1);
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
