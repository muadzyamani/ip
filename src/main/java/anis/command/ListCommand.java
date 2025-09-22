package anis.command;

import anis.storage.Storage;
import anis.task.TaskList;
import anis.ui.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList.getTasks());
    }
}
