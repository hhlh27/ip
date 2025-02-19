package dazai;

import java.io.IOException;

/**
 * Command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Creates an UnmarkCommand to unmark a task as not done.
     *
     * @param taskIndex The index of the task to unmark (1-based).
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1; // Convert to 0-based index
    }

    /**
     * Executes the unmarking of a task and updates the UI and storage.
     *
     * @param taskList The task list to operate on.
     * @param ui       The UI to display messages.
     * @param storage  The storage to save the updated tasks.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DazAiException {
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            return "Invalid task number! Please enter a valid index.";
        }

        try {
            Task task = taskList.getTask(taskIndex);
            task.markAsNotDone();
            storage.saveTasks(taskList);
            return "OK, I've marked this task as not done yet:\n  " + task;
        } catch (IOException e) {
            throw new DazAiException("Failed to save tasks.");
        }
    }
}

