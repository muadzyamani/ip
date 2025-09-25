package anis.exception;

/**
 * Thrown when a task command is issued without a description.
 */
public class EmptyDescriptionException extends AnisException {
    /**
     * Constructs an {@code EmptyDescriptionException} with a message
     * specific to the given task type.
     *
     * @param taskType the type of task missing a description
     */
    public EmptyDescriptionException(String taskType) {
        super("The description of a " + taskType + " cannot be empty.");
    }
}
