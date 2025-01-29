package dazai;

import java.io.IOException;

public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = taskList.getTask(taskIndex);
            task.markAsDone();
            ui.showMessage("Nice! I've marked this task as done:\n  " + task);
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            ui.showMessage("Invalid task number! Please enter a valid index.");
        } catch (IOException e) {
            ui.showMessage("Failed to save tasks.");
        }
    }
}