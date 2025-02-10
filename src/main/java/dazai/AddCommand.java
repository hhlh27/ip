package dazai;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {

    private final String type;
    private final String description;
    private final String startDateTime; // Renamed for clarity
    private final String endDateTime;   // Renamed for clarity

    /**
     * Constructs an AddCommand with the specified task type, description, and dates.
     *
     * @param type        The type of task (event, deadline, or todo).
     * @param description The description of the task.
     * @param startDateTime The first date/time parameter (for deadlines and events).
     * @param endDateTime  The second date/time parameter (for events only).
     */
    public AddCommand(String type, String description, String startDateTime, String endDateTime) {
        this.type = Objects.requireNonNull(type, "Task type cannot be null").toLowerCase().trim();
        this.description = Objects.requireNonNull(description, "Task description cannot be null").trim();
        this.startDateTime = (startDateTime != null) ? startDateTime.trim() : null;
        this.endDateTime = (endDateTime != null) ? endDateTime.trim() : null;
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
                return new Event(description, startDateTime, endDateTime);
            case "deadline":
                validateDeadline();
                return new Deadline(description, startDateTime);
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
        List<String> eventDetails = Arrays.asList(startDateTime, endDateTime);

        // Use Streams to check if any of the event details are null or empty
        if (eventDetails.stream().anyMatch(s -> s == null || s.isEmpty())) {
            throw new DazAiException("Invalid event format! Use: event <desc> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
        }
    }

    /**
     * Validates a deadline task input.
     *
     * @throws DazAiException If the deadline details are invalid.
     */
    private void validateDeadline() throws DazAiException {
        if (startDateTime == null || startDateTime.isEmpty()) {
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