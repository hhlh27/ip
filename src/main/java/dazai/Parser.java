package dazai;

/**
 * A parser that interprets user input and returns the corresponding command.
 * It breaks down the input string into meaningful components to determine which
 * action the user wants to take.
 */
public class Parser {

    /**
     * Parses the input string and returns the appropriate command.
     *
     * @param input The user input string.
     * @return A command corresponding to the user input.
     * @throws DazAIException If the input cannot be parsed into a valid command.
     */
    public static Command parse(String input) throws DazAIException {
        String[] words = input.split(" ", 2);
        String commandWord = words[0].toLowerCase();

        switch (commandWord) {
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            case "todo":
                return parseTodo(words);
            case "deadline":
                return parseDeadline(words);
            case "event":
                return parseEvent(words);
            case "mark":
                return parseMark(words);
            case "unmark":
                return parseUnmark(words);
            case "delete":
                return parseDelete(words);
            case "find":
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    throw new DazAIException("Please specify a keyword to search.");
                }
                return new FindCommand(words[1].trim());
            default:
                throw new DazAIException("I'm sorry, but I don't understand that command.");
        }
    }

    /**
     * Parses the input for a 'todo' command and returns the corresponding AddCommand.
     *
     * @param words The split input words array.
     * @return The AddCommand for the todo task.
     * @throws DazAIException If the todo description is missing or invalid.
     */
    private static Command parseTodo(String[] words) throws DazAIException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new DazAIException("The description of a todo cannot be empty.");
        }
        return new AddCommand("todo", words[1].trim(), null, null);
    }

    /**
     * Parses the input for a 'deadline' command and returns the corresponding AddCommand.
     *
     * @param words The split input words array.
     * @return The AddCommand for the deadline task.
     * @throws DazAIException If the deadline description or date is missing.
     */
    private static Command parseDeadline(String[] words) throws DazAIException {
        if (words.length < 2) {
            throw new DazAIException("Invalid deadline format! Use: deadline <desc> /by yyyy-MM-dd HHmm");
        }
        String[] parts = words[1].split(" /by ", 2);
        if (parts.length < 2) {
            throw new DazAIException("Invalid deadline format! Use: deadline <desc> /by yyyy-MM-dd HHmm");
        }
        return new AddCommand("deadline", parts[0].trim(), parts[1].trim(), null);
    }

    /**
     * Parses the input for an 'event' command and returns the corresponding AddCommand.
     *
     * @param words The split input words array.
     * @return The AddCommand for the event task.
     * @throws DazAIException If the event description or time is missing.
     */
    private static Command parseEvent(String[] words) throws DazAIException {
        if (words.length < 2) {
            throw new DazAIException("Invalid event format! Use: event <desc> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
        }
        String[] parts = words[1].split(" /from | /to ", 3);
        if (parts.length < 3) {
            throw new DazAIException("Invalid event format! Use: event <desc> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
        }
        return new AddCommand("event", parts[0].trim(), parts[1].trim(), parts[2].trim());
    }

    /**
     * Parses the input for a 'mark' command and returns the corresponding MarkCommand.
     *
     * @param words The split input words array.
     * @return The MarkCommand for the task.
     * @throws DazAIException If no task number is specified.
     */
    private static Command parseMark(String[] words) throws DazAIException {
        if (words.length < 2) {
            throw new DazAIException("Please specify a task number to mark.");
        }
        return new MarkCommand(Integer.parseInt(words[1].trim()));
    }

    /**
     * Parses the input for an 'unmark' command and returns the corresponding UnmarkCommand.
     *
     * @param words The split input words array.
     * @return The UnmarkCommand for the task.
     * @throws DazAIException If no task number is specified.
     */
    private static Command parseUnmark(String[] words) throws DazAIException {
        if (words.length < 2) {
            throw new DazAIException("Please specify a task number to unmark.");
        }
        return new UnmarkCommand(Integer.parseInt(words[1].trim()));
    }

    /**
     * Parses the input for a 'delete' command and returns the corresponding DeleteCommand.
     *
     * @param words The split input words array.
     * @return The DeleteCommand for the task.
     * @throws DazAIException If no task number is specified.
     */
    private static Command parseDelete(String[] words) throws DazAIException {
        if (words.length < 2) {
            throw new DazAIException("Please specify a task number to delete.");
        }
        return new DeleteCommand(Integer.parseInt(words[1].trim()));
    }
}