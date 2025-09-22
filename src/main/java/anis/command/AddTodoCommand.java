package anis.command;

import anis.exception.AnisException;
import anis.exception.EmptyDescriptionException;
import anis.storage.Storage;
import anis.task.TaskList;
import anis.task.Todo;
import anis.ui.Ui;

public class AddTodoCommand extends Command {
    private final String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

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
