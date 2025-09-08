package anis.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTypeIcon() {
        return TaskType.TODO.getIcon();
    }
}
