package dazai;

import java.io.IOException;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param taskIndex The 1-based index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1;
    }

    /**
     * Executes the delete command by removing a task from the task list and updating storage.
     *
     * @param taskList The task list containing the tasks.
     * @param ui The user interface to show messages.
     * @param storage The storage system to save task updates.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task removedTask = taskList.deleteTask(taskIndex);
            ui.showMessage("Noted. I've removed this task:\n  " + removedTask);
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage("Invalid task number! Please enter a valid index.");
        } catch (IOException e) {
            ui.showMessage("Failed to save tasks.");
        }
    }
}
