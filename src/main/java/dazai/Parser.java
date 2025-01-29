package dazai;

public class Parser {
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
            default:
                throw new DazAIException("I'm sorry, but I don't understand that command.");
        }
    }

    private static Command parseTodo(String[] words) throws DazAIException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new DazAIException("The description of a todo cannot be empty.");
        }
        return new AddCommand("todo", words[1].trim(), null, null);
    }

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

    private static Command parseMark(String[] words) throws DazAIException {
        if (words.length < 2) {
            throw new DazAIException("Please specify a task number to mark.");
        }
        return new MarkCommand(Integer.parseInt(words[1].trim()));
    }

    private static Command parseUnmark(String[] words) throws DazAIException {
        if (words.length < 2) {
            throw new DazAIException("Please specify a task number to unmark.");
        }
        return new UnmarkCommand(Integer.parseInt(words[1].trim()));
    }

    private static Command parseDelete(String[] words) throws DazAIException {
        if (words.length < 2) {
            throw new DazAIException("Please specify a task number to delete.");
        }
        return new DeleteCommand(Integer.parseInt(words[1].trim()));
    }
}