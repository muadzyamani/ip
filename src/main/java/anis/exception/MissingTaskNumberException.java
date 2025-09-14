package anis.exception;

public class MissingTaskNumberException extends AnisException {
    public MissingTaskNumberException(String command) {
        super("Please specify which task to " + command + ".");
    }
}
