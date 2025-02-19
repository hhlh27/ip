package dazai;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by displaying a farewell message.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The user interface to display messages.
     * @param storage The storage system (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Returns {@code true} to indicate that this command will terminate the application.
     *
     * @return {@code true}, indicating an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

