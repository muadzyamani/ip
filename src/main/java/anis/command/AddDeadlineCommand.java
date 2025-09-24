package anis.command;

import anis.exception.AnisException;
import anis.exception.InvalidFormatException;
import anis.storage.Storage;
import anis.task.Deadline;
import anis.task.Task;
import anis.task.TaskList;
import anis.ui.Ui;

import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {
    private final String description;

    public AddDeadlineCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AnisException {
        String[] parts = description.split("/by", 2);
        if (parts.length < 2) {
            throw new InvalidFormatException("deadline", "<desc> /by yyyy-MM-dd");
        }

        try {
            Task task = new Deadline(parts[0].trim(), parts[1].trim());
            taskList.addTask(task);
            storage.save(taskList.getTasks());
            ui.showAdded(task, taskList.getTaskCount());
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("deadline", "<desc> /by yyyy-MM-dd");
        }
    }
}
