package dazai;

import java.io.IOException;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {

    private final String type;
    private final String description;
    private final String dateTime1; // Used for deadlines and events
    private final String dateTime2; // Used for events only

    /**
     * Constructs an AddCommand with the specified task type, description, and dates.
     *
     * @param type The type of task (event, deadline, or todo).
     * @param description The description of the task.
     * @param dateTime1 The first date/time parameter (for deadlines and events).
     * @param dateTime2 The second date/time parameter (for events only).
     */
    public AddCommand(String type, String description, String dateTime1, String dateTime2) {
        assert type != null && !type.isEmpty() : "Task type cannot be null or empty";
        assert description != null && !description.isEmpty() : "Task description cannot be null or empty";
        this.type = type;
        this.description = description;
        this.dateTime1 = dateTime1;
        this.dateTime2 = dateTime2;
    }

    /**
     * Executes the add command by creating and adding a task to the task list.
     * Also saves the updated task list to storage.
     *
     * @param taskList The task list to modify.
     * @param ui The user interface for displaying messages.
     * @param storage The storage handler for saving tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DazAiException {
        try {
            Task task = createTask();
            taskList.addTask(task);
            storage.saveTasks(taskList);  // Save tasks only once, after processing
            return "Added: " + task;
        } catch (DazAiException | IOException e) {
            return "Error: " + e.getMessage();
        }
    }
    /**
     * Creates a task based on the provided type and details.
     *
     * @return The created Task object.
     * @throws DazAiException If the task type or parameters are invalid.
     */
    private Task createTask() throws DazAiException {
        switch (type) {
            case "event":
                if (dateTime1 == null || dateTime2 == null) {
                    throw new DazAiException("Invalid event format! Use: event <desc> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
                }
                return new Event(description, dateTime1, dateTime2);
            case "deadline":
                if (dateTime1 == null) {
                    throw new DazAiException("Invalid deadline format! Use: deadline <desc> /by yyyy-MM-dd HHmm");
                }
                return new Deadline(description, dateTime1);
            case "todo":
                if (description.isEmpty()) {
                    throw new DazAiException("The description of a todo cannot be empty.");
                }
                return new ToDo(description);
            default:
                throw new DazAiException("Invalid task type! Use: event, deadline, or todo.");
        }
    }

    /**
     * Indicates whether this command should exit the program.
     *
     * @return {@code false}, as the add command does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}