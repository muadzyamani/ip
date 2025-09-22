package anis.command;

import anis.exception.AnisException;
import anis.storage.Storage;
import anis.task.TaskList;
import anis.ui.Ui;

public abstract class Command {
    public abstract void execute (TaskList taskList, Ui ui, Storage storage) throws AnisException;

    public boolean isExit() {
        return false;
    }
}
