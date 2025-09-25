package anis.exception;

/**
 * Thrown when a command requires a task number, but none is provided.
 */
public class MissingTaskNumberException extends AnisException {
    /**
     * Constructs a {@code MissingTaskNumberException} with a message
     * referencing the command that is missing a task number.
     *
     * @param command the command that lacked a task number
     */
    public MissingTaskNumberException(String command) {
        super("Please specify which task to " + command + ".");
    }
}
