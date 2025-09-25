package anis.task;

/**
 * The {@code Task} class represents an abstract task with a description
 * and a completion status. Subclasses provide specific task types such as
 * deadlines, events, or to-dos.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new {@code Task} with the given description.
     * By default, the task is not marked as done.
     *
     * @param description the description of the task
     */
    public Task (String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of this task.
     *
     * @return the task description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the status icon representing whether the task is done.
     *
     * @return {@code "X"} if the task is done, otherwise a space character {@code " "}
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }


    /**
     * Returns the type icon representing the task type.
     * Subclasses must implement this to identify themselves
     * (Example: {@code "T"} for to-do, {@code "D"} for deadline).
     *
     * @return the type icon of the task
     */
    public abstract String getTypeIcon();

    /**
     * Returns the string representation of this task.
     * Format: {@code [Type][Status] description}
     *
     * Example: {@code [T][X] read book}
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "[" + getTypeIcon() + "][" + this.getStatusIcon() + "] " + this.description;
    }

    /**
     * Returns the string representation of this task for saving to file.
     * Subclasses must implement their own save format.
     *
     * @return the save format string for this task
     */
    public abstract String toSaveFormat();
}
