package dazai;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void parse_validListCommand_success() throws DazAIException {
        Command command = Parser.parse("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    public void parse_invalidCommand_exceptionThrown() {
        Exception exception = assertThrows(DazAIException.class, () -> {
            Parser.parse("invalidCommand");
        });

        assertEquals("I'm sorry, but I don't understand that command.", exception.getMessage());
    }
}