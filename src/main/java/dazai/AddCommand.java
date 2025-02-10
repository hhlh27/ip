package dazai;

import java.io.IOException;
import java.util.Objects;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {

    private final String type;
    private final String description;
    private final String fromDateTime; // Used for deadlines and events
    private final String toDateTime;   // Used for events only

    /**
     * Constructs an AddCommand with the specified task type, description, and dates.
     *
     * @param type        The type of task (event, deadline, or todo).
     * @param description The description of the task.
     * @param fromDateTime The first date/time parameter (for deadlines and events).
     * @param toDateTime  The second date/time parameter (for events only).
     */
    public AddCommand(String type, String description, String fromDateTime, String toDateTime) {
        this.type = Objects.requireNonNull(type, "Task type cannot be null").toLowerCase().trim();
        this.description = Objects.requireNonNull(description, "Task description cannot be null").trim();
        this.fromDateTime = (fromDateTime != null) ? fromDateTime.trim() : null;
        this.toDateTime = (toDateTime != null) ? toDateTime.trim() : null;
    }

    /**
     * Executes the add command by creating and adding a task to the task list.
     * Also saves the updated task list to storage.
     *
     * @param taskList The task list to modify.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage handler for saving tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = createTask();
            taskList.addTask(task);
            storage.saveTasks(taskList); // Save tasks only once, after processing
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
            validateEvent();
            return new Event(description, fromDateTime, toDateTime);
        case "deadline":
            validateDeadline();
            return new Deadline(description, fromDateTime);
        case "todo":
            validateToDo();
            return new ToDo(description);
        default:
            throw new DazAiException("Invalid task type! Use: event, deadline, or todo.");
    }
    }

    /**
     * Validates an event task input.
     *
     * @throws DazAiException If the event details are invalid.
     */
    private void validateEvent() throws DazAiException {
        if (fromDateTime == null || toDateTime == null || fromDateTime.isEmpty() || toDateTime.isEmpty()) {
            throw new DazAiException("Invalid event format! Use: event <desc> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
        }
    }

    /**
     * Validates a deadline task input.
     *
     * @throws DazAiException If the deadline details are invalid.
     */
    private void validateDeadline() throws DazAiException {
        if (fromDateTime == null || fromDateTime.isEmpty()) {
            throw new DazAiException("Invalid deadline format! Use: deadline <desc> /by yyyy-MM-dd HHmm");
        }
    }

    /**
     * Validates a todo task input.
     *
     * @throws DazAiException If the todo description is empty.
     */
    private void validateToDo() throws DazAiException {
        if (description.isEmpty()) {
            throw new DazAiException("The description of a todo cannot be empty.");
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