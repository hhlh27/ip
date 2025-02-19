package dazai;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object to hold tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes a task from the task list by its index.
     *
     * @param index The index of the task to be deleted.
     * @return The task that was removed, or null if the index is invalid.
     */
    public Task deleteTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            throw new IndexOutOfBoundsException("Invalid task index.");
        }
        return tasks.remove(index);
    }

    /**
     * Gets a task from the task list by its index.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index, or null if the index is invalid.
     */
    public Task getTask(int index) {
        if (index < 0 || index >= tasks.size()) {
            System.out.println("Invalid task index.");
            return null;
        }
        return tasks.get(index);
    }

    /**
     * Gets all tasks in the task list.
     *
     * @return An unmodifiable list of all tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(tasks); // To prevent external modification of the tasks list
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return True if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
}