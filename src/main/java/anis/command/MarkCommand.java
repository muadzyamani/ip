package anis.command;

import anis.exception.AnisException;
import anis.exception.InvalidTaskNumberException;
import anis.exception.MissingTaskNumberException;
import anis.storage.Storage;
import anis.task.Task;
import anis.task.TaskList;
import anis.ui.Ui;

/**
 * The {@code MarkCommand} class represents a command to mark a task as done
 * or unmark it as not done.
 * <p>
 * It validates the task number and updates both the task list and the storage.
 */
public class MarkCommand extends Command {
    private final int taskNumber;
    private final boolean isMark;

    /**
     * Constructs a {@code MarkCommand} for marking or unmarking a task.
     *
     * @param description the string representing the task number
     * @param isMark {@code true} to mark the task as done, {@code false} to unmark
     * @throws AnisException if the description is blank or not a valid task number
     */
    public MarkCommand(String description, boolean isMark) throws AnisException {
        this.isMark = isMark;

        if (description.isBlank()) {
            throw new MissingTaskNumberException(isMark ? "mark" : "unmark");
        }

        try {
            this.taskNumber = Integer.parseInt(description.trim());
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
    }

    /**
     * Executes the mark/unmark action on the specified task.
     * <p>
     * Updates the task's status, saves the updated task list to storage,
     * and displays the result using the UI.
     *
     * @param tasksList the list of tasks to update
     * @param ui the user interface for displaying messages
     * @param storage the storage handler for saving tasks
     * @throws AnisException if the task number is invalid
     */
    @Override
    public void execute(TaskList tasksList, Ui ui, Storage storage) throws AnisException {
        if (tasksList.isInvalidTaskNumber(taskNumber)) {
            throw new InvalidTaskNumberException();
        }

        Task task = tasksList.getTask(taskNumber);
        if (isMark) {
            task.markAsDone();
        } else {
            task.markAsNotDone();
        }

        storage.save(tasksList.getTasks());
        ui.showMark(task, isMark);
    }
}
