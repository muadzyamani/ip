package anis.command;

import anis.exception.AnisException;
import anis.exception.InvalidFormatException;
import anis.storage.Storage;
import anis.task.Task;
import anis.task.TaskList;
import anis.ui.Ui;

import java.util.List;

/**
 * The {@code FindCommand} searches for tasks that contain a keyword
 * and displays the matching tasks.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a {@code FindCommand} with the specified keyword.
     *
     * @param keyword the search keyword
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command, displaying matching tasks.
     *
     * @param taskList the task list to search
     * @param ui the user interface for displaying matching tasks
     * @param storage the storage (not used in this command)
     * @throws AnisException if the keyword is empty
     */
    @Override
    public void execute (TaskList taskList, Ui ui, Storage storage) throws AnisException {
        String trimmedKeyword = keyword.trim();
        if (trimmedKeyword.isEmpty()) {
            throw new InvalidFormatException("find", "<keyword>");
        }

        List<Task> foundTasks = taskList.findTasks(trimmedKeyword);
        ui.showMatchingTasks(foundTasks);
    }
}
