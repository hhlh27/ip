package dazai;

/**
 * Represents a command to list all tasks in the task list.
 * This command retrieves and displays the entire task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command by displaying the list of tasks.
     * This method calls the UI to show the tasks in the task list.
     *
     * @param tasks The list of tasks to display.
     * @param ui The user interface for displaying the tasks.
     * @param storage The storage handler for saving tasks (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks);
    }
}