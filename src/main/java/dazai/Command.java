package dazai;

/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {
    /**
     * Executes the command with the given task list, UI, and storage.
     *
     * @param tasks The task list to modify.
     * @param ui The user interface for displaying messages.
     * @param storage The storage handler for saving tasks.
     * @throws DazAiException If an error occurs during execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DazAiException;

    /**
     * Indicates whether this command should exit the program.
     *
     * @return {@code false}, as most commands do not exit the program by default.
     */
    public boolean isExit() {
        return false;
    }
}
