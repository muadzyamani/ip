package anis.command;

import anis.exception.AnisException;
import anis.exception.InvalidFormatException;
import anis.storage.Storage;
import anis.task.Event;
import anis.task.Task;
import anis.task.TaskList;
import anis.ui.Ui;

public class AddEventCommand extends Command {
    private final String description;

    public AddEventCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws AnisException {
        String[] parts = description.split("/from", 2);
        if (parts.length < 2) {
            throw new InvalidFormatException("event", "<desc> /from <start> /to <end>");
        }
        String[] fromTo = parts[1].split("/to", 2);
        if (fromTo.length < 2) {
            throw new InvalidFormatException("event", "<desc> /from <start> /to <end>");
        }

        Task task = new Event(parts[0].trim(), fromTo[0].trim(), fromTo[1].trim());
        taskList.addTask(task);
        storage.save(taskList.getTasks());
        ui.showAdded(task, taskList.getTaskCount());
    }
}
