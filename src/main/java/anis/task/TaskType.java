package anis.task;

public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private final String icon;
    TaskType (String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    /**
     * Returns the TaskType corresponding to the given type string.
     * Used for parsing saved file data.
     *
     * @param typeStr the string representation of the task type
     * @return the corresponding TaskType, or null if not found
     */
    public static TaskType fromString(String typeStr) {
        for (TaskType type : values()) {
            if (type.icon.equals(typeStr)) {
                return type;
            }
        }
        return null;
    }
}
