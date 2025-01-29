package dazai;

import java.io.IOException;

public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = taskList.getTask(taskIndex);
            task.markAsNotDone();
            ui.showMessage("OK, I've marked this task as not done yet:\n  " + task);
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage("Invalid task number! Please enter a valid index.");
        } catch (IOException e) {
            ui.showMessage("Failed to save tasks.");
        }
    }
}
