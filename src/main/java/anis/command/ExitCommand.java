package anis.command;

import anis.storage.Storage;
import anis.task.TaskList;
import anis.ui.Ui;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // No action needed for exit command
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
