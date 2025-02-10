package dazai;

import java.io.IOException;

/**
 * The main class for the DazAi chatbot application.
 * Initializes the required components and manages the main application loop.
 */
public class DazAi {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;
    /**
     * Constructs a new DazAi instance.
     * Initializes the UI, storage, and task list, and loads tasks from storage.
     */
    public DazAi() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList();
        this.parser = new Parser();
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
            } catch (DazAiException e) {
                ui.showMessage("Invalid command: " + e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The entry point of the application.
     * Creates a new instance of DazAi and starts the chatbot.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {

        new DazAi().run(); // Run the bot

    }
    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            return command.execute(taskList, ui, storage);
        } catch (DazAiException e) {
            return "Oops! " + e.getMessage();
        }
    }
}