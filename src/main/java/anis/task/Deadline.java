package anis.task;

import anis.storage.Storage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Deadline} class represents a task that must be completed
 * by a specific due date.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a {@code Deadline} with the given description and due date.
     * <p>
     * The date must be provided in {@code yyyy-MM-dd} format.
     *
     * @param description the description of the deadline task
     * @param by the due date in {@code yyyy-MM-dd} format
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by); // expects yyyy-MM-dd format
    }

    /**
     * Returns the type icon representing a deadline task.
     *
     * @return the string icon for a deadline task
     */
    @Override
    public String getTypeIcon() {
        return TaskType.DEADLINE.getIcon();
    }

    /**
     * Returns the string representation of this deadline.
     * Format includes type, status, description, and the formatted due date.
     * <p>
     * Example: {@code [D][ ] submit report (by: Sep 30 2025)}
     *
     * @return the string representation of the deadline
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return super.toString() + " (by: " + by.format(formatter) + ")";
    }

    /**
     * Returns the string representation of this deadline for saving to file.
     * <p>
     * Format: {@code D | status | description | by}
     * <ul>
     *   <li>{@code status} is {@code 1} if done, {@code 0} if not done</li>
     *   <li>{@code by} is stored in {@code yyyy-MM-dd} format</li>
     * </ul>
     *
     * @return the save format string for this deadline
     */
    @Override
    public String toSaveFormat() {
        return TaskType.DEADLINE.getIcon() + " | " + Storage.getStatusString(isDone) + " | " + description + " | " + by;
    }
}