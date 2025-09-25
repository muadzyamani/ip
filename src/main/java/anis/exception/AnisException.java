package anis.exception;

/**
 * The {@code AnisException} class represents custom exceptions
 * specific to the Anis task management application.
 * <p>
 * It is used to signal errors in parsing, command execution,
 * or other application-specific scenarios.
 */
public class AnisException extends Exception {
    /**
     * Constructs a new {@code AnisException} with the specified detail message.
     *
     * @param message the detail message describing the cause of the exception
     */
    public AnisException(String message) {
        super(message);
    }
}
