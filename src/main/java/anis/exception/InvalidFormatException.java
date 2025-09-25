package anis.exception;

/**
 * Thrown when a task command is issued with an invalid format.
 */
public class InvalidFormatException extends AnisException {
    /**
     * Constructs an {@code InvalidFormatException} with a message
     * describing the correct format for the given task type.
     *
     * @param taskType the type of task with an invalid format
     * @param correctFormat the correct format for the task command
     */
    public InvalidFormatException(String taskType, String correctFormat) {
        super("Please use the format: " + taskType + " " + correctFormat);
    }
}
