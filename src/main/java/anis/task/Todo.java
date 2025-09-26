package anis.task;

import anis.storage.Storage;

/**
 * The {@code Todo} class represents a task without any date or time constraints.
 * <p>
 * It is the simplest type of task, defined only by a description.
 */
public class Todo extends Task {
    /**
     * Constructs a {@code Todo} task with the given description.
     * The task is initially not marked as done.
     *
     * @param description the description of the to-do task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the type icon representing a to-do task.
     *
     * @return the string icon for a to-do task
     */
    @Override
    public String getTypeIcon() {
        return TaskType.TODO.getIcon();
    }

    /**
     * Returns the string representation of this to-do for saving to file.
     * <p>
     * Format: {@code T | status | description}
     * <ul>
     *   <li>{@code status} is {@code 1} if done, {@code 0} if not done</li>
     * </ul>
     *
     * @return the save format string for this to-do
     */
    @Override
    public String toSaveFormat() {
        return TaskType.TODO.getIcon() + " | " + Storage.getStatusString(isDone) + " | " + description;
    }
}