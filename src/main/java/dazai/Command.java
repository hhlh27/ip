package dazai;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DazAIException;

    public boolean isExit() {
        return false;
    }
}