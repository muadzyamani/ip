package anis;

import anis.storage.Storage;
import anis.command.Parser;
import anis.task.TaskList;
import anis.ui.Ui;

import java.util.Scanner;

public class Anis {
    private static final Scanner scanner = new Scanner(System.in);

    public static void runChat() {
        Ui ui = new Ui();
        Storage storage = new Storage("./data/anis.txt");
        TaskList taskList = new TaskList(storage.load());
        Parser handler = new Parser(taskList, ui, storage);

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
