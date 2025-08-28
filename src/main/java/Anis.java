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

    private static final String[] tasks = new String[100];
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

    public static void addTask(String task) {
        if (taskCount >= tasks.length) {
            printBorder();
            System.out.println("\t Task list is full. Cannot add more tasks.");
            printBorder();
            return;
        }

        tasks[taskCount] = task;
        taskCount++;
        System.out.println("\t added: " + task);
        printBorder();
    }

    public static void listTasks() {
        printBorder();
        for (int i = 0; i < taskCount; i++) {
            System.out.println("\t " + (i + 1) + ". " + tasks[i]);
        }
        printBorder();
    }

    public static void echoCommand(String command) {
        printBorder();
        System.out.println("\t " + command);
        printBorder();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        displayWelcome();

        String userInput;
        while (true) {
            userInput = scanner.nextLine().trim();
            if (userInput.equalsIgnoreCase("bye")) {
                displayGoodbye();
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                listTasks();
            } else {
                addTask(userInput);
            }
        }

        scanner.close();
    }
}