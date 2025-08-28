import java.util.Scanner;

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

    private static final Task[] tasks = new Task[100];
    private static int taskCount = 0;

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

    public static void addTask(String description) {
        if (taskCount >= tasks.length) {
            printBorder();
            System.out.println("\t Task list is full. Cannot add more tasks.");
            printBorder();
            return;
        }

        tasks[taskCount] = new Task(description);
        taskCount++;

        printBorder();
        System.out.println("\t added: " + description);
        printBorder();
    }

    public static void listTasks() {
        printBorder();
        for (int i = 0; i < taskCount; i++) {
            System.out.println("\t " + (i + 1) + ". " + tasks[i]);
        }
        printBorder();
    }

    public static void markTask(int taskNumber) {
        if (taskNumber <= 0 || taskNumber > taskCount) {
            printBorder();
            System.out.println("\t Invalid task number.");
            printBorder();
            return;
        }

        int taskIndex = taskNumber - 1;
        tasks[taskIndex].markAsDone();

        printBorder();
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t   " + tasks[taskIndex]);
        printBorder();
    }

    public static void unmarkTask(int taskNumber) {
        if (taskNumber <= 0 || taskNumber > taskCount) {
            printBorder();
            System.out.println("\t Invalid task number.");
            printBorder();
            return;
        }

        int taskIndex = taskNumber - 1;
        tasks[taskIndex].unmarkAsDone();

        printBorder();
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.println("\t   " + tasks[taskIndex]);
        printBorder();
    }

    public static void main(String[] args) {
        displayWelcome();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine().trim();
            String[] words = userInput.split(" ", 2);
            String command = words[0].toLowerCase();

            switch (command) {
            case "bye":
                displayGoodbye();
                scanner.close();
                return;
            case "list":
                listTasks();
                break;
            case "mark":
            case "unmark":
                try {
                    if (words.length < 2) {
                        printBorder();
                        System.out.println("\t Please specify which task to " + command + ".");
                        printBorder();
                        break;
                    }

                    int taskNumber = Integer.parseInt(words[1]);
                    if (command.equals("mark")) {
                        markTask(taskNumber);
                    } else {
                        unmarkTask(taskNumber);
                    }
                } catch (NumberFormatException e) {
                    printBorder();
                    System.out.println("\t Please enter a valid task number after 'mark' or 'unmark'.");
                    printBorder();
                }
                break;

            default:
                addTask(userInput);
                break;
            }
        }
    }
}