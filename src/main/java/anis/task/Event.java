package anis.task;

import anis.storage.Storage;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The {@code Event} class represents a task that occurs over a time period,
 * with a start date ({@code from}) and an end date ({@code to}).
 */
public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    /**
     * Constructs an {@code Event} with the given description, start date, and end date.
     * <p>
     * Dates must be provided in {@code yyyy-MM-dd} format.
     *
     * @param description the description of the event
     * @param from the start date of the event in {@code yyyy-MM-dd} format
     * @param to the end date of the event in {@code yyyy-MM-dd} format
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from); // expects yyyy-MM-dd
        this.to = LocalDate.parse(to);
    }

    /**
     * Returns the type icon representing an event task.
     *
     * @return the string icon for an event task
     */
    @Override
    public String getTypeIcon() {
        return TaskType.EVENT.getIcon();
    }

    /**
     * Returns the string representation of this event.
     * Format includes type, status, description, and formatted start/end dates.
     * <p>
     * Example: {@code [E][ ] project meeting (from: Sep 15 2025 to: Sep 16 2025)}
     *
     * @return the string representation of the event
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return super.toString() + " (from: " + from.format(formatter)
                + " to: " + to.format(formatter) + ")";
    }

    /**
     * Returns the string representation of this event for saving to file.
     * <p>
     * Format: {@code E | status | description | from | to}
     * <ul>
     *   <li>{@code status} is {@code 1} if done, {@code 0} if not done</li>
     *   <li>{@code from} and {@code to} are in {@code yyyy-MM-dd} format</li>
     * </ul>
     *
     * @return the save format string for this event
     */
    @Override
    public String toSaveFormat() {
        return TaskType.EVENT.getIcon() + " | " + Storage.getStatusString(isDone) + " | " + description + " | " + from + " | " + to;
    }
}