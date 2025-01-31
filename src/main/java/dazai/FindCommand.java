package dazai;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword.trim().toLowerCase(); // Ensure the search is case-insensitive
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        // Search through all tasks for matching descriptions
        for (Task task : taskList.getAllTasks()) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        // Display the results to the user
        ui.showLine();
        if (matchingTasks.isEmpty()) {
            ui.showMessage("No tasks found matching: " + keyword);
        } else {
            ui.showMessage("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                ui.showMessage((i + 1) + ". " + matchingTasks.get(i));
            }
        }
        ui.showLine();
    }
}