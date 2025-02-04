package dazai;

import java.io.IOException;

/**
 * The main class for the DazAI chatbot application.
 * Initializes the required components and manages the main application loop.
 */
public class DazAI {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructs a new DazAI instance.
     * Initializes the UI, storage, and task list, and loads tasks from storage.
     */
    public DazAI() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList();
        loadTasksFromStorage();
    }

    /**
     * Loads tasks from storage into the task list.
     * Displays an error message if loading fails.
     */
    private void loadTasksFromStorage() {
        try {
            for (Task task : storage.loadTasks()) {
                taskList.addTask(task);
            }
        } catch (IOException e) {
            ui.showMessage("Error loading tasks from storage.");
        }
    }

    /**
     * Runs the main loop of the chatbot.
     * Continuously reads user input, processes commands, and executes them
     * until an exit command is received.
     */
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

    /**
     * The entry point of the application.
     * Creates a new instance of DazAI and starts the chatbot.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new DazAI().run(); // Run the bot
    }
}