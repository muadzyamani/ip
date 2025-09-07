public class EmptyDescriptionException extends AnisException {
    public EmptyDescriptionException(String taskType) {
        super("The description of a " + taskType + " cannot be empty.");
    }
}
