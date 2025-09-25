package anis.command;

import anis.storage.Storage;
import anis.task.TaskList;
import anis.ui.Ui;

/**
 * The {@code ListCommand} displays all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying all tasks using the UI.
     *
     * @param taskList the task list to display
     * @param ui the user interface for displaying tasks
     * @param storage the storage (not used in this command)
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList.getTasks());
    }
}
