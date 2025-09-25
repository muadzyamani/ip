package anis.exception;

/**
 * Thrown when a task number provided by the user is invalid
 * (Example: out of range or negative).
 */
public class InvalidTaskNumberException extends AnisException {
    /**
     * Constructs an {@code InvalidTaskNumberException} with a default message.
     */
    public InvalidTaskNumberException() {
        super("Invalid task number.");
    }
}
