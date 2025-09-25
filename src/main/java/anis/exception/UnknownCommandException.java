package anis.exception;

/**
 * Thrown when the user enters a command that is not recognized
 * by the application.
 */
public class UnknownCommandException extends AnisException {
    /**
     * Constructs an {@code UnknownCommandException} with a default message.
     */
    public UnknownCommandException() {
        super("I'm sorry, I don't understand that command.");
    }
}
