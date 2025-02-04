package dazai;

/**
 * Represents an exception specific to the DazAi application.
 */
public class DazAiException extends Exception {
    /**
     * Constructs a new DazAiException with the specified detail message.
     *
     * @param message The detail message describing the exception.
     */
    public DazAiException(String message) {
        super(message);
    }
}
