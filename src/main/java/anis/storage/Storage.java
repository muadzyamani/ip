package anis.storage;

import anis.task.Deadline;
import anis.task.Event;
import anis.task.Task;
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
                Files.createDirectories(filePath.getParent());
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
        String[] parts = line.split(" \\| ");
        try {
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            switch (type) {
            case "T":
                Task todo = new Todo(description);
                if (isDone) {
                    todo.markAsDone();
                }
                return todo;
            case "D":
                String by = parts[3];
                Task deadline = new Deadline(description, by);
                if (isDone) {
                    deadline.markAsDone();
                }
                return deadline;
            case "E":
                String from = parts[3];
                String to = parts[4];
                Task event = new Event(description, from, to);
                if (isDone) {
                    event.markAsDone();
                }
                return event;
            default:
                return null;
            }

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
}
