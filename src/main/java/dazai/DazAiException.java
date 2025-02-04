package dazai;

/**
 * Represents an exception specific to the DazAI application.
 */
public class DazAIException extends Exception {
    /**
     * Constructs a new DazAIException with the specified detail message.
     *
     * @param message The detail message describing the exception.
     */
    public DazAIException(String message) {
        super(message);
    }
}
