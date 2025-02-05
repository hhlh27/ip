package dazai;

import java.io.IOException;

/**
 * Represents a command to mark a task as done.
 * This command updates the task's status and saves the updated task list.
 */
public class MarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a MarkCommand with the specified task index.
     * The task index is adjusted by 1 to convert from the user input (1-based) to the internal (0-based) index.
     *
     * @param taskIndex The index of the task to mark as done (1-based).
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1;  // Adjusting the user input to 0-based index
    }

    /**
     * Executes the mark command by marking the specified task as done.
     * Also saves the updated task list to storage.
     *
     * @param taskList The list of tasks to modify.
     * @param ui The user interface for displaying messages.
     * @param storage The storage handler for saving tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DazAiException {
        try {
            Task task = taskList.getTask(taskIndex);
            task.markAsDone();
            storage.saveTasks(taskList);
            return "Nice! I've marked this task as done:\n  " + task;
        } catch (IndexOutOfBoundsException e) {
            throw new DazAiException("Invalid task number! Please enter a valid index.");
        } catch (IOException e) {
            throw new DazAiException("Failed to save tasks.");
        }
    }
}