package anis.ui;

import anis.task.Task;

import java.util.List;

/**
 * The {@code Ui} class handles all user interactions by
 * displaying messages, prompts, and task lists to the console.
 * <p>
 * It is responsible only for output formatting and presentation,
 * while other components manage logic and data.
 */
public class Ui {
    private static final String BORDER = "    ____________________________________________________________";
    private static final String LOGO =
            """
                    ░███               ░██
                   ░██░██
                  ░██  ░██  ░████████  ░██ ░███████
                 ░█████████ ░██    ░██ ░██░██
                 ░██    ░██ ░██    ░██ ░██ ░███████
                 ░██    ░██ ░██    ░██ ░██       ░██
                 ░██    ░██ ░██    ░██ ░██ ░███████
            """;

    /**
     * Prints a border line to the console.
     */
    public void printBorder() {
        System.out.println(BORDER);
    }

    /**
     * Displays a single message with indentation.
     *
     * @param message the message to display
     */
    private void show(String message) {
        System.out.println("\t " + message);
    }

    /**
     * Displays the welcome message and program logo.
     */
    public void showWelcome() {
        System.out.print(LOGO);
        printBorder();
        show("Hi! My name is Anis, and I'm here to help.");
        show("What's on your mind?");
        printBorder();
    }

    /**
     * Displays the goodbye message when the program exits.
     */
    public void showGoodbye() {
        printBorder();
        show("Glad I could assist! Have a wonderful day.");
        show("Feel free to reach out anytime.");
        printBorder();
    }

    /**
     * Displays a message confirming that a task was added.
     *
     * @param task       the task that was added
     * @param totalTasks the total number of tasks after addition
     */
    public void showAdded(Task task, int totalTasks) {
        printBorder();
        show("Got it. I've added this task:");
        show("  " + task);
        show("Now you have " + totalTasks + (totalTasks == 1 ? " task" : " tasks") + " in the list.");
        printBorder();
    }

    /**
     * Displays all tasks in the task list.
     *
     * @param tasks the list of tasks to display
     */
    public void showTaskList(List<Task> tasks) {
        printBorder();
        if (tasks.isEmpty()) {
            show("No tasks in your list yet.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                show((i + 1) + ". " + tasks.get(i));
            }
        }
        printBorder();
    }

    /**
     * Displays a message when a task is marked as done or not done.
     *
     * @param task   the task that was updated
     * @param isDone {@code true} if the task was marked as done,
     *               {@code false} if marked as not done
     */
    public void showMark(Task task, boolean isDone) {
        printBorder();
        if (isDone) {
            show("Nice! I've marked this task as done:");
        } else {
            show("OK, I've marked this task as not done yet:");
        }
        show("  " + task);
        printBorder();
    }

    /**
     * Displays an error message.
     *
     * @param message the error message to display
     */
    public void showError(String message) {
        printBorder();
        show(message);
        printBorder();
    }

    /**
     * Displays a message confirming that a task was deleted.
     *
     * @param task       the task that was removed
     * @param totalTasks the total number of tasks remaining after deletion
     */
    public void showDeleted(Task task, int totalTasks) {
        printBorder();
        show("Noted. I've removed this task:");
        show("  " + task);
        show("Now you have " + totalTasks + (totalTasks == 1 ? " task" : " tasks") + " in the list.");
        printBorder();
    }

    /**
     * Displays a list of tasks that match a search query.
     *
     * @param matchedTasks the list of matching tasks
     */
    public void showMatchingTasks(List<Task> matchedTasks) {
        if (matchedTasks.isEmpty()) {
            printBorder();
            show("\t No matching tasks found.");
            printBorder();
            return;
        }

        printBorder();
        show("\t Here are the matching tasks in your list:");
        for (int i = 0; i < matchedTasks.size(); i++) {
            show("\t " + (i + 1) + "." + matchedTasks.get(i));
        }
        printBorder();
    }
}
