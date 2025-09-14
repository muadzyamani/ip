package anis.ui;

import anis.task.Task;

import java.util.List;

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

    public void printBorder() {
        System.out.println(BORDER);
    }

    private void show(String message) {
        System.out.println("\t " + message);
    }

    public void showWelcome() {
        System.out.print(LOGO);
        printBorder();
        show("Hi! My name is Anis, and I'm here to help.");
        show("What's on your mind?");
        printBorder();
    }

    public void showGoodbye() {
        printBorder();
        show("Glad I could assist! Have a wonderful day.");
        show("Feel free to reach out anytime.");
        printBorder();
    }

    public void showAdded(Task task, int totalTasks) {
        printBorder();
        show("Got it. I've added this task:");
        show("  " + task);
        show("Now you have " + totalTasks + (totalTasks == 1 ? " task" : " tasks") + " in the list.");
        printBorder();
    }

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

    public void showError(String message) {
        printBorder();
        show(message);
        printBorder();
    }

    public void showDeleted(Task task, int totalTasks) {
        printBorder();
        show("Noted. I've removed this task:");
        show("  " + task);
        show("Now you have " + totalTasks + (totalTasks == 1 ? " task" : " tasks") + " in the list.");
        printBorder();
    }
}
