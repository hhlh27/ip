package dazai;

public class ListCommand extends Command {

    /**
     * Executes the list command by displaying the list of tasks.
     * This method returns the tasks as a string to be displayed in the UI.
     *
     * @param tasks The list of tasks to display.
     * @param ui The user interface for displaying the tasks (not used in this command).
     * @param storage The storage handler for saving tasks (not used in this command).
     * @return The list of tasks in a string format.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.size() == 0) {
            return "There are no tasks in your list.";
        }

        StringBuilder taskListString = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            taskListString.append((i + 1) + ". " + tasks.getTask(i) + "\n");
        }
        return taskListString.toString();
    }

    /**
     * Indicates whether this command should exit the program.
     *
     * @return {@code false}, as the list command does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

