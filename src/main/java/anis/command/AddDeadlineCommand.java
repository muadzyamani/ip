package anis.command;

import anis.exception.AnisException;
import anis.exception.InvalidFormatException;
import anis.storage.Storage;
import anis.task.Deadline;
import anis.task.Task;
import anis.task.TaskList;
import anis.ui.Ui;

import java.time.format.DateTimeParseException;

/**
 * The {@code AddDeadlineCommand} adds a new Deadline task to the task list.
 * <p>
 * The input format must be: {@code <desc> /by yyyy-MM-dd}.
 */
public class AddDeadlineCommand extends Command {
    private final String description;

    /**
     * Constructs an {@code AddDeadlineCommand} with the given description string.
     *
     * @param description the input string describing the deadline
     */
    public AddDeadlineCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command by parsing the description, creating a new Deadline task,
     * adding it to the task list, saving the updated list, and displaying confirmation via the UI.
     *
     * @param taskList the list of tasks
     * @param ui the user interface for displaying messages
     * @param storage the storage for persisting tasks
     * @throws AnisException if the input format is invalid or the date parsing fails
     */
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
