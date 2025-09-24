package anis.command;

import anis.exception.AnisException;
import anis.exception.InvalidFormatException;
import anis.storage.Storage;
import anis.task.Task;
import anis.task.TaskList;
import anis.ui.Ui;

import java.util.List;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute (TaskList taskList, Ui ui, Storage storage) throws AnisException {
        String trimmedKeyword = keyword.trim();
        if (trimmedKeyword.isEmpty()) {
            throw new InvalidFormatException("find", "<keyword>");
        }

        List<Task> foundTasks = taskList.findTasks(keyword);
        ui.showMatchingTasks(foundTasks);
    }
}
