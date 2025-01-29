public class Task {

    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    // Getter for description
    public String getDescription() {
        return description;
    }
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // Mark done tasks with 'X'
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }
    // Getter for isDone status
    public boolean isDone() {
        return isDone;
    }
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}


