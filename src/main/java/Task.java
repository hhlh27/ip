public class Task {

    String name;
    boolean isDone;

    Task(String n) {
        this.name = n;
        this.isDone = false;
    }

    void markDone() {
        this.isDone = true;
    }

    void markNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + name;
    }
}


