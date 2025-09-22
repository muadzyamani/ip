package anis.command;

import anis.exception.AnisException;
import anis.exception.InvalidTaskNumberException;
import anis.exception.MissingTaskNumberException;
import anis.storage.Storage;
import anis.task.Task;
import anis.task.TaskList;
import anis.ui.Ui;

public class MarkCommand extends Command {
    private final int taskNumber;
    private final boolean isMark;

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
