public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String getTypeIcon() {
        return TaskType.DEADLINE.getIcon();
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
