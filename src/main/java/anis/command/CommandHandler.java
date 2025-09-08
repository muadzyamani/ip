package anis.command;

import anis.exception.AnisException;
import anis.exception.EmptyDescriptionException;
import anis.exception.InvalidFormatException;
import anis.exception.InvalidTaskNumberException;
import anis.exception.MissingTaskNumberException;
import anis.task.Todo;
import anis.ui.Ui;
import anis.exception.UnknownCommandException;
import anis.task.Deadline;
import anis.task.Event;
import anis.task.Task;
import anis.task.TaskManager;

public class CommandHandler {
    private final TaskManager taskManager;
    private final Ui ui;

    public CommandHandler(TaskManager taskManager, Ui ui) {
        this.taskManager = taskManager;
        this.ui = ui;
    }

    public boolean processCommand(String userInput) {
        String[] words = userInput.split(" ", 2);
        String command = words[0].toLowerCase();
        String description = (words.length > 1) ? words[1] : "";

        try {
            switch (command) {
            case "bye":
                return false;
            case "list":
                ui.showTaskList(taskManager.getTasks());
                break;
            case "mark":
                handleMark(true, words);
                break;
            case "unmark":
                handleMark(false, words);
                break;
            case "todo":
                addTodo(description);
                break;
            case "deadline":
                addDeadline(description);
                break;
            case "event":
                addEvent(description);
                break;
            default:
                throw new UnknownCommandException();
            }
        } catch (AnisException e) {
            ui.showError(e.getMessage());
        }

        return true;
    }

    private void addTodo(String description) throws AnisException {
        if (description.isBlank()) {
            throw new EmptyDescriptionException("todo");
        }
        Task task = new Todo(description);
        taskManager.addTask(task);
        ui.showAdded(task, taskManager.getTaskCount());
    }

    private void addDeadline(String description) throws AnisException {
        String[] parts = description.split("/by", 2);
        if (parts.length < 2) {
            throw new InvalidFormatException("deadline", "<desc> /by <time>");
        }
        Task task = new Deadline(parts[0].trim(), parts[1].trim());
        taskManager.addTask(task);
        ui.showAdded(task, taskManager.getTaskCount());
    }

    private void addEvent(String description) throws AnisException {
        String[] parts = description.split("/from", 2);
        if (parts.length < 2) {
            throw new InvalidFormatException("event", "<desc> /from <start> /to <end>");
        }
        String[] fromTo = parts[1].split("/to", 2);
        if (fromTo.length < 2) {
            throw new InvalidFormatException("event", "<desc> /from <start> /to <end>");
        }
        Task task = new Event(parts[0].trim(), fromTo[0].trim(), fromTo[1].trim());
        taskManager.addTask(task);
        ui.showAdded(task, taskManager.getTaskCount());
    }

    private void handleMark(boolean isMark, String[] words) throws AnisException {
        if (words.length < 2) {
            throw new MissingTaskNumberException(isMark);
        }
        try {
            int taskNumber = Integer.parseInt(words[1]);
            if (taskManager.isInvalidTaskNumber(taskNumber)) {
                throw new InvalidTaskNumberException();
            }
            Task task = taskManager.getTask(taskNumber);
            if (isMark) {
                task.markAsDone();
            } else {
                task.markAsNotDone();
            }
            ui.showMark(task, isMark);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
    }
}
