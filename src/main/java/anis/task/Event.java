package anis.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDate.parse(from); // expects yyyy-MM-dd
        this.to = LocalDate.parse(to);
    }

    @Override
    public String getTypeIcon() {
        return TaskType.EVENT.getIcon();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        return super.toString() + " (from: " + from.format(formatter)
                + " to: " + to.format(formatter) + ")";
    }

    @Override
    public String toSaveFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }
}
