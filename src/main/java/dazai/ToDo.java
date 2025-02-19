package dazai;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the specified description.
     *
     * @param description The description of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description of a ToDo task cannot be empty.");
        }
    }

    /**
     * Returns a string representation of the ToDo task.
     *
     * @return A formatted string representing the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

