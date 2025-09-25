package anis.command;

import anis.exception.AnisException;
import anis.exception.InvalidTaskNumberException;
import anis.exception.MissingTaskNumberException;
import anis.storage.Storage;
import anis.task.Task;
import anis.task.TaskList;
import anis.ui.Ui;

/**
 * The {@code DeleteCommand} removes a task from the task list by its number.
 */
public class DeleteCommand extends Command {
    private final int taskNumber;

    /**
     * Constructs a {@code DeleteCommand} for the specified task number.
     *
     * @param description the string representing the task number to delete
     * @throws AnisException if the description is blank or not a valid task number
     */
    public DeleteCommand(String description) throws AnisException {
        if (description.isBlank()) {
            throw new MissingTaskNumberException("delete");
        }

        try {
            this.taskNumber = Integer.parseInt(description.trim());
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
    }

    /**
     * Executes the delete command, removing the task from the list,
     * saving the updated list, and displaying a confirmation via the UI.
     *
     * @param taskList the task list containing the task to delete
     * @param ui the user interface for displaying messages
     * @param storage the storage handler for saving the updated task list
     * @throws AnisException if the task number is invalid
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AnisException {
        if (taskList.isInvalidTaskNumber(taskNumber)) {
            throw new InvalidTaskNumberException();
        }

        Task task = taskList.getTask(taskNumber);
        taskList.deleteTask(taskNumber);
        storage.save(taskList.getTasks());
        ui.showDeleted(task, taskList.getTaskCount());
    }
}
