package anis;

import anis.command.Command;
import anis.command.Parser;
import anis.exception.AnisException;
import anis.storage.Storage;
import anis.task.TaskList;
import anis.ui.Ui;

import java.util.ArrayList;
import java.util.Scanner;

public class Anis {
    private static final Scanner scanner = new Scanner(System.in);

    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;

    public Anis(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showError("Could not load tasks from file. Starting with empty task list.");
            taskList = new TaskList(new ArrayList<>());
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = scanner.nextLine().trim();
                Command command = Parser.parse(userInput);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (AnisException e) {
                ui.showError(e.getMessage());
            }
        }
        ui.showGoodbye();
    }

    public static void main(String[] args) {
        new Anis("./data/anis.txt").run();
    }
}
