package anis.task;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code TaskList} class manages a collection of {@link Task} objects.
 * <p>
 * It provides methods for adding, removing, retrieving, counting,
 * and searching tasks in the list.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Constructs a {@code TaskList} with an initial list of tasks.
     * The provided list is copied to ensure encapsulation.
     *
     * @param tasks the initial tasks to populate the list
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task the task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the list based on its task number.
     *
     * @param taskNumber the 1-based index of the task to delete
     */
    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber - 1);
    }

    /**
     * Checks if a given task number is invalid (out of range).
     *
     * @param taskNumber the 1-based index of the task to validate
     * @return {@code true} if the task number is invalid, {@code false} otherwise
     */
    public boolean isInvalidTaskNumber(int taskNumber) {
        return taskNumber <= 0 || taskNumber > tasks.size();
    }

    /**
     * Retrieves a task from the list based on its task number.
     *
     * @param taskNumber the 1-based index of the task to retrieve
     * @return the {@link Task} at the specified position
     */
    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber - 1);
    }

    /**
     * Returns the total number of tasks in the list.
     *
     * @return the number of tasks
     */
    public int getTaskCount() {
        return tasks.size();
    }

    /**
     * Returns the full list of tasks.
     *
     * @return the list of tasks
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Finds and returns all tasks that contain the given keyword in their description.
     * The search is case-insensitive.
     *
     * @param keyword the keyword to search for
     * @return a list of matching tasks
     */
    public List<Task> findTasks(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
