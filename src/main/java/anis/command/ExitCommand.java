package anis.command;

import anis.storage.Storage;
import anis.task.TaskList;
import anis.ui.Ui;

/**
 * The {@code ExitCommand} signals the application to terminate.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command.
     * No action is needed because exit is handled by {@link Command#isExit()}.
     *
     * @param taskList the task list (not used)
     * @param ui the user interface (not used)
     * @param storage the storage (not used)
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        // No action needed for exit command
    }

    /**
     * Indicates that this command exits the application.
     *
     * @return {@code true} since this command signals application exit
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
