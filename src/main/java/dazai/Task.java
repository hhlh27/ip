package dazai;

/**
 * Represents a task with a description and completion status.
 */
public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Creates a new task with the given description.
     *
     * @param description The description of the task.
     * @throws IllegalArgumentException if the description is null or empty.
     */
    public Task(String description) {
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Task description cannot be empty or null.");
        }
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the status icon of the task (X if done, space if not).
     *
     * @return The status icon.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // Mark done tasks with 'X'
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns whether the task is done.
     *
     * @return True if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A string representing the task in the format "[X] description" or "[ ] description".
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}