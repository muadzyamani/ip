package anis.command;

import anis.exception.AnisException;
import anis.exception.InvalidTaskNumberException;
import anis.exception.MissingTaskNumberException;
import anis.storage.Storage;
import anis.task.Task;
import anis.task.TaskList;
import anis.ui.Ui;

public class DeleteCommand extends Command {
    private final int taskNumber;

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
