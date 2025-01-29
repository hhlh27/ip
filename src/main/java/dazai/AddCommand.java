package dazai;

import java.io.IOException;

public class AddCommand extends Command {
    private final String type;
    private final String description;
    private final String dateTime1; // Used for deadlines and events
    private final String dateTime2; // Used for events only

    public AddCommand(String type, String description, String dateTime1, String dateTime2) {
        this.type = type;
        this.description = description;
        this.dateTime1 = dateTime1;
        this.dateTime2 = dateTime2;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = createTask();
            taskList.addTask(task);
            ui.showMessage("Added: " + task);
            storage.saveTasks(taskList);  // Save tasks only once, after processing
        } catch (DazAIException | IOException e) {
            ui.showMessage("Error: " + e.getMessage());
        }
    }

    private Task createTask() throws DazAIException {
        switch (type) {
            case "event":
                if (dateTime1 == null || dateTime2 == null) {
                    throw new DazAIException("Invalid event format! Use: event <desc> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
                }
                return new Event(description, dateTime1, dateTime2);
            case "deadline":
                if (dateTime1 == null) {
                    throw new DazAIException("Invalid deadline format! Use: deadline <desc> /by yyyy-MM-dd HHmm");
                }
                return new Deadline(description, dateTime1);
            case "todo":
                if (description.isEmpty()) {
                    throw new DazAIException("The description of a todo cannot be empty.");
                }
                return new ToDo(description);
            default:
                throw new DazAIException("Invalid task type! Use: event, deadline, or todo.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
