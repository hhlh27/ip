import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDateTime by;
    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDateTime.parse(by, INPUT_FORMAT);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ")";
    }
    // Getter for description
    public String getBy() {
        return by.format(INPUT_FORMAT); //
    }
}

