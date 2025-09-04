import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Anis {
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

    private static final List<Task> tasks = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);

    static void printBorder() {
        System.out.println(BORDER);
    }

    public static void printLogo() {
        System.out.print(LOGO);
    }

    public static void displayWelcome() {
        printLogo();
        printBorder();
        System.out.println("\t Hi! My name is Anis, and I'm here to help.");
        System.out.println("\t What's on your mind?");
        printBorder();
    }

    public static void displayGoodbye() {
        System.out.println("\t Glad I could assist! Have a wonderful day.");
        System.out.println("\t Feel free to reach out anytime.");
        printBorder();
    }

    private static void addTodo(String description) {
        Task newTask = new Todo(description);
        addTaskToList(newTask);
    }

    private static void addDeadline(String description) {
        String[] deadlineParts = description.split("/by", 2);
        if (deadlineParts.length < 2) {
            System.out.println("\t Please use the format: deadline <desc> /by <time>");
            return;
        }
        Task newTask = new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
        addTaskToList(newTask);
    }

    private static void addTaskToList(Task newTask) {
        tasks.add(newTask);
        printBorder();
        System.out.println("\t Got it. I've added this task:");
        System.out.println("\t   " + newTask);
        System.out.println("\t Now you have " + tasks.size() +
                (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
        printBorder();
    }

    private static void addEvent(String description) {
        String[] eventParts = description.split("/from", 2);
        if (eventParts.length < 2) {
            System.out.println("\t Please use the format: event <desc> /from <start> /to <end>");
            return;
        }
        String[] fromTo = eventParts[1].split("/to", 2);
        if (fromTo.length < 2) {
            System.out.println("\t Please use the format: event <desc> /from <start> /to <end>");
            return;
        }
        Task newTask = new Event(eventParts[0].trim(), fromTo[0].trim(), fromTo[1].trim());
        addTaskToList(newTask);
    }

    public static void listTasks() {
        printBorder();
        if (tasks.isEmpty()) {
            System.out.println("\t No tasks in your list yet.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("\t " + (i + 1) + ". " + tasks.get(i));
            }
        }
        printBorder();
    }

    public static boolean isInvalidTaskNumber(int taskNumber) {
        if (taskNumber <= 0 || taskNumber > tasks.size()) {
            printBorder();
            System.out.println("\t Invalid task number.");
            printBorder();
            return true;
        }
        return false;
    }

    public static void markTask(int taskNumber) {
        if (isInvalidTaskNumber(taskNumber)) {
            return;
        }

        int taskIndex = taskNumber - 1;
        Task taskToMark = tasks.get(taskIndex);
        taskToMark.markAsDone();

        printBorder();
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t   " + taskToMark);
        printBorder();
    }

    public static void unmarkTask(int taskNumber) {
        if (isInvalidTaskNumber(taskNumber)) {
            return;
        }

        int taskIndex = taskNumber - 1;
        Task taskToUnmark = tasks.get(taskIndex);
        taskToUnmark.markAsNotDone();

        printBorder();
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.println("\t   " + taskToUnmark);
        printBorder();
    }

    public static void handleMark(boolean isMark, String[] words) {
        String command = isMark ? "mark" : "unmark";

        try {
            if (words.length < 2) {
                printBorder();
                System.out.println("\t Please specify which task to " + command + ".");
                printBorder();
                return;
            }

            int taskNumber = Integer.parseInt(words[1]);
            if (isMark) {
                markTask(taskNumber);
            } else {
                unmarkTask(taskNumber);
            }
        } catch (NumberFormatException e) {
            printBorder();
            System.out.println("\t Please enter a valid task number after '" + command + "'.");
            printBorder();
        }
    }

    public static void handleAddTask(String command, String description) {
        if (description.isBlank()) {
            printBorder();
            System.out.println("\t The description of a " + command + " cannot be empty.");
            printBorder();
            return;
        }

        switch (command) {
        case "todo":
            addTodo(description);
            break;
        case "deadline":
            addDeadline(description);
            break;
        case "event":
            addEvent(description);
            break;
        }
    }


    public static void processCommand(String userInput) {
        String[] words = userInput.split(" ", 2);
        String command = words[0].toLowerCase();
        String description = (words.length > 1) ? words[1] : "";

        switch (command) {
        case "bye":
            printBorder();
            displayGoodbye();
            scanner.close();
            return;
        case "list":
            listTasks();
            break;
        case "mark":
            handleMark(true, words);
            break;
        case "unmark":
            handleMark(false, words);
            break;
        case "todo":
        case "deadline":
        case "event":
            handleAddTask(command, description);
            break;
        default:
            printBorder();
            System.out.println("\t I'm sorry, I don't understand that command.");
            printBorder();
            break;
        }
    }

    public static void runChat() {
        String userInput;
        do {
            userInput = scanner.nextLine().trim();
            processCommand(userInput);
        } while (!userInput.equals("bye"));
    }

    public static void main(String[] args) {
        displayWelcome();
        runChat();
    }
}