package anis;

import anis.Storage.Storage;
import anis.command.CommandHandler;
import anis.task.TaskManager;
import anis.ui.Ui;

import java.util.Scanner;

public class Anis {
    private static final Scanner scanner = new Scanner(System.in);

    public static void runChat() {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/anis.txt");
        TaskManager taskManager = new TaskManager(storage.load());
        CommandHandler handler = new CommandHandler(taskManager, ui, storage);

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
