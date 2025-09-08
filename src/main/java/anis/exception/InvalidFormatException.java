package anis.exception;

public class InvalidFormatException extends AnisException {
    public InvalidFormatException(String taskType, String correctFormat) {
        super("Please use the format: " + taskType + " " + correctFormat);
    }
}
