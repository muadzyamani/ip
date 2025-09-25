package anis.command;

import anis.exception.AnisException;
import anis.exception.EmptyDescriptionException;
import anis.storage.Storage;
import anis.task.TaskList;
import anis.task.Todo;
import anis.ui.Ui;

/**
 * The {@code AddTodoCommand} adds a new Todo task to the task list.
 */
public class AddTodoCommand extends Command {
    private final String description;

    /**
     * Constructs an {@code AddTodoCommand} with the given description.
     *
     * @param description the description of the new Todo task
     */
    public AddTodoCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command by creating a new Todo task, adding it to the task list,
     * saving the updated list, and displaying confirmation via the UI.
     *
     * @param taskList the list of tasks
     * @param ui the user interface for displaying messages
     * @param storage the storage for persisting tasks
     * @throws AnisException if the description is blank
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AnisException {
        if (description.isBlank()) {
            throw new EmptyDescriptionException("todo");
        }

        Todo task = new Todo(description);
        taskList.addTask(task);
        storage.save(taskList.getTasks());
        ui.showAdded(task, taskList.getTaskCount());
    }
}
