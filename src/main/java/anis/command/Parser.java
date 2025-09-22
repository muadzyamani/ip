package anis.command;

import anis.exception.AnisException;
import anis.exception.UnknownCommandException;


public class Parser {
    public static Command parse(String userInput) throws AnisException {
        String[] words = userInput.split(" ", 2);
        String command = words[0].toLowerCase();
        String description = (words.length > 1) ? words[1] : "";

        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(description, true);
        case "unmark":
            return new MarkCommand(description, false);
        case "todo":
            return new AddTodoCommand(description);
        case "deadline":
            return new AddDeadlineCommand(description);
        case "event":
            return new AddEventCommand(description);
        case "delete":
            return new DeleteCommand(description);
        default:
            throw new UnknownCommandException();
        }
    }
}
