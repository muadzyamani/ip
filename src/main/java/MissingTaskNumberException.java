public class MissingTaskNumberException extends AnisException {
    public MissingTaskNumberException(boolean isMark) {
        super("Please specify which task to " + (isMark ? "mark" : "unmark") + ".");
    }
}
