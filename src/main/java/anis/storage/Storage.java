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

public class Storage {
    private final Path filePath;

    public Storage(String filePath) {
        this.filePath = Path.of(filePath);
    }

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
