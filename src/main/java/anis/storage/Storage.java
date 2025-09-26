package anis.storage;

import anis.task.Deadline;
import anis.task.Event;
import anis.task.Task;
import anis.task.TaskType;
import anis.task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Storage} class handles reading tasks from and writing tasks to a file.
 * <p>
 * It ensures that tasks persist between program runs by saving them in a
 * text-based format and reloading them at startup.
 */
public class Storage {
    private final Path filePath;

    private static final String TASK_DONE = "1";
    private static final String TASK_NOT_DONE = "0";
    private static final String FIELD_SEPARATOR = " \\| ";

    /**
     * Constructs a {@code Storage} instance with the given file path.
     *
     * @param filePath the path of the file where tasks are stored
     */
    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }

    /**
     * Loads tasks from the save file into memory.
     * <p>
     * If the save file does not exist, it is created along with its parent directories.
     * Corrupted lines in the save file are skipped.
     *
     * @return a list of tasks loaded from the file (may be empty if none found)
     */
    public List<Task> load() {
        List<Task> tasks = new ArrayList<>();
        if (!Files.exists(filePath)) {
            try {
                Path parent = filePath.getParent();
                if (parent != null) {
                    Files.createDirectories(parent);
                }
                Files.createFile(filePath);
            } catch (IOException e) {
                System.out.println("Error creating save file: " + e.getMessage());
                return tasks;
            }
            return tasks;
        }

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading save file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Parses a single line from the save file into a {@link Task} object.
     * <p>
     * Expected formats:
     * <ul>
     *   <li>Todo: {@code T | status | description}</li>
     *   <li>Deadline: {@code D | status | description | by}</li>
     *   <li>Event: {@code E | status | description | from | to}</li>
     * </ul>
     * where {@code status} is {@code 1} if done, {@code 0} if not done.
     *
     * @param line the line of text representing a saved task
     * @return the parsed task, or {@code null} if parsing fails
     */
    private Task parseTask(String line) {
        String[] parts = line.split(FIELD_SEPARATOR);
        try {
            TaskType taskType = TaskType.fromString(parts[0]);
            if (taskType == null) {
                return null;
            }

            boolean isDone = parts[1].equals(TASK_DONE);
            String description = parts[2];

            Task task = null;
            switch (taskType) {
            case TODO:
                task = new Todo(description);
                break;
            case DEADLINE:
                String by = parts[3];
                task = new Deadline(description, by);
                break;
            case EVENT:
                String from = parts[3];
                String to = parts[4];
                task = new Event(description, from, to);
                break;
            }

            if (task != null && isDone) {
                task.markAsDone();
            }
            return task;

        } catch (Exception e) {
            System.out.println("Corrupted line: " + line);
            return null;
        }
    }

    /**
     * Saves the given list of tasks to the save file.
     * <p>
     * Each task is converted to its save format using {@link Task#toSaveFormat()}.
     * Existing file contents are overwritten.
     *
     * @param tasks the list of tasks to save
     */
    public void save(List<Task> tasks) {
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Task task : tasks) {
                writer.write(task.toSaveFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    /**
     * Returns the string representation for a task's completion status.
     *
     * @param isDone true if the task is done, false otherwise
     * @return "1" if done, "0" if not done
     */
    public static String getStatusString(boolean isDone) {
        return isDone ? TASK_DONE : TASK_NOT_DONE;
    }
}