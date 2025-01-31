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
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            ui.showMessage("Invalid task number! Please enter a valid index.");
            return;
        }

        try {
            Task task = taskList.getTask(taskIndex);
            task.markAsNotDone();
            ui.showMessage("OK, I've marked this task as not done yet:\n  " + task);
            storage.saveTasks(taskList);
        } catch (IOException e) {
            ui.showMessage("Failed to save tasks.");
        }
    }
}