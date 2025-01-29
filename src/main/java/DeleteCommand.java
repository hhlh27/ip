import java.io.IOException;

public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

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