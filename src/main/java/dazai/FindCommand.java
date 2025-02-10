package dazai;

import java.util.ArrayList;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("Keyword cannot be empty.");
        }
        this.keyword = keyword.trim().toLowerCase(); // Ensure the search is case-insensitive
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList.getAllTasks()) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            return "No tasks found matching: " + keyword;
        }

        StringBuilder response = new StringBuilder("Here are the matching tasks:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            response.append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
        }
        return response.toString();
    }
}