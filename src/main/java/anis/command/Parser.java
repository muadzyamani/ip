package anis.command;

import anis.exception.AnisException;
import anis.exception.UnknownCommandException;

/**
 * The {@code Parser} class is responsible for interpreting user input
 * and converting it into executable {@link Command} objects.
 * <p>
 * It also validates the input format and throws exceptions for unknown commands.
 */
public class Parser {

    /**
     * Parses a line of user input and returns the corresponding {@link Command}.
     *
     * @param userInput the raw input string entered by the user
     * @return the {@link Command} corresponding to the user input
     * @throws AnisException if the command is unknown or the input format is invalid
     */
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
        case "find":
            return new FindCommand(description);
        default:
            throw new UnknownCommandException();
        }
    }
}
