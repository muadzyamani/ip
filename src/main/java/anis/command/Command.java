package anis.command;

import anis.exception.AnisException;
import anis.storage.Storage;
import anis.task.TaskList;
import anis.ui.Ui;

/**
 * The abstract {@code Command} class represents an executable action
 * in the Anis application.
 * <p>
 * Each concrete subclass implements the {@link #execute} method.
 */
public abstract class Command {

    /**
     * Executes this command on the given task list, UI, and storage.
     *
     * @param taskList the list of tasks to operate on
     * @param ui the user interface for displaying messages
     * @param storage the storage handler for saving tasks
     * @throws AnisException if an application-specific error occurs
     */
    public abstract void execute (TaskList taskList, Ui ui, Storage storage) throws AnisException;

    /**
     * Returns whether this command signals the application to exit.
     *
     * @return {@code true} if this command exits the application, {@code false} otherwise
     */
    public boolean isExit() {
        return false;
    }
}
