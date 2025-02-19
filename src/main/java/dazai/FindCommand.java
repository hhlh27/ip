package dazai;

import java.util.ArrayList;

/**
 * Represents a command to find tasks in the task list that match a given keyword.
 * The search is case-insensitive.
 */
public class FindCommand extends Command {
    private final String keyword;
    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for. Must not be null or empty.
     * @throws IllegalArgumentException if the keyword is null or empty.
     */
    public FindCommand(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("Keyword cannot be empty.");
        }
        this.keyword = keyword.trim().toLowerCase(); // Ensure the search is case-insensitive
    }

    /**
     * Executes the find command by searching the task list for tasks whose descriptions
     * contain the specified keyword. The search is case-insensitive.
     *
     * @param taskList The task list to search for matching tasks
     * @return A string representing the list of matching tasks, or a message indicating
     *         that no tasks were found.
     */
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

