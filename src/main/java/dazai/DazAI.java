package dazai;

import java.io.IOException;

public class DazAI {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public DazAI() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList();
        loadTasksFromStorage();
    }

    private void loadTasksFromStorage() {
        try {
            for (Task task : storage.loadTasks()) {
                taskList.addTask(task);
            }
        } catch (IOException e) {
            ui.showMessage("Error loading tasks from storage.");
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command command = Parser.parse(fullCommand);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (DazAIException e) {
                ui.showMessage("Invalid command: " + e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new DazAI().run(); // Run the bot
    }
}

