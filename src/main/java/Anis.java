import java.util.Scanner;

public class Anis {
    private static final Scanner scanner = new Scanner(System.in);

    public static void runChat() {
        TaskManager taskManager = new TaskManager();
        Ui ui = new Ui();
        CommandHandler handler = new CommandHandler(taskManager, ui);

        ui.showWelcome();

        String userInput;
        boolean continueChat = true;
        while (continueChat) {
            userInput = scanner.nextLine().trim();
            continueChat = handler.processCommand(userInput);
        }

        ui.showGoodbye();
    }

    public static void main(String[] args) {
        runChat();
    }
}
