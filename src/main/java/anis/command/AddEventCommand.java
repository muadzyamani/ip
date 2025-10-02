package anis.command;

import anis.exception.AnisException;
import anis.exception.InvalidFormatException;
import anis.storage.Storage;
import anis.task.Event;
import anis.task.Task;
import anis.task.TaskList;
import anis.ui.Ui;

import java.time.format.DateTimeParseException;

/**
 * The {@code AddEventCommand} adds a new Event task to the task list.
 * <p>
 * The input format must be: {@code <desc> /from yyyy-MM-dd /to yyyy-MM-dd}.
 */
public class AddEventCommand extends Command {
    private final String description;

    /**
     * Constructs an {@code AddEventCommand} with the given description string.
     *
     * @param description the input string describing the event
     */
    public AddEventCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command by parsing the description, creating a new Event task,
     * adding it to the task list, saving the updated list, and displaying confirmation via the UI.
     *
     * @param taskList the list of tasks
     * @param ui the user interface for displaying messages
     * @param storage the storage for persisting tasks
     * @throws AnisException if the input format is invalid or the date parsing fails
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AnisException {
        String[] parts = description.split("/from", 2);
        if (parts.length < 2) {
            throw new InvalidFormatException("event", "<desc> /from yyyy-MM-dd /to yyyy-MM-dd");
        }
        String[] fromTo = parts[1].split("/to", 2);
        if (fromTo.length < 2) {
            throw new InvalidFormatException("event", "<desc> /from yyyy-MM-dd /to yyyy-MM-dd");
        }

        try {
            Task task = new Event(parts[0].trim(), fromTo[0].trim(), fromTo[1].trim());
            taskList.addTask(task);
            storage.save(taskList.getTasks());
            ui.showAdded(task, taskList.getTaskCount());
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("event", "<desc> /from yyyy-MM-dd /to yyyy-MM-dd");
        } catch (IllegalArgumentException e) {
            throw new AnisException("Start date must be before or equal to end date.");
        }
    }
}
