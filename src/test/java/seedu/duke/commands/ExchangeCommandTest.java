import org.junit.jupiter.api.Test;
import seedu.duke.commands.ExchangeCommand;
import seedu.duke.exceptions.InvalidNumberException;
import seedu.duke.exceptions.InvalidExchangeArgumentException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.fail;

public class ExchangeCommandTest {

    @Test
    public void testParseAmount_nonNumericInput_shouldThrowInvalidNumberException() {
        try {
            ExchangeCommand cmd = new ExchangeCommand("exchange THB SGD xyz");
            assertThrows(InvalidNumberException.class, () -> cmd.parseAmount());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testParseAmount_negativeInput_shouldThrowInvalidNumberException() {
        try {
            ExchangeCommand cmd = new ExchangeCommand("exchange THB SGD -1.0");
            assertThrows(InvalidNumberException.class, () -> cmd.parseAmount());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testFormatInput_incorrectSyntax_shouldThrowInvalidExchangeArgumentException() {
        try {
            ExchangeCommand cmd1 = new ExchangeCommand("exchange THB SGD 1.0 2.0");
            ExchangeCommand cmd2 = new ExchangeCommand("exchange THB SGD");
            ExchangeCommand cmd3 = new ExchangeCommand("exchange THB");
            ExchangeCommand cmd4 = new ExchangeCommand("exchange");
            assertThrows(InvalidExchangeArgumentException.class, () -> cmd1.formatInput());
            assertThrows(InvalidExchangeArgumentException.class, () -> cmd2.formatInput());
            assertThrows(InvalidExchangeArgumentException.class, () -> cmd3.formatInput());
            assertThrows(InvalidExchangeArgumentException.class, () -> cmd4.formatInput());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testFormatInput_invalidCurrency_shouldThrowIllegalArgumentException() {
        try {
            ExchangeCommand cmd = new ExchangeCommand("exchange THB XYZ 1.0");
            assertThrows(IllegalArgumentException.class, () -> cmd.formatInput());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testFormatInput_correctSyntax_shouldNotThrow() {
        try {
            ExchangeCommand cmd = new ExchangeCommand("exchange THB SGD 1.0");
            assertDoesNotThrow(() -> cmd.formatInput());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testParseAmount_correctSyntax_shouldNotThrow() {
        try {
            ExchangeCommand cmd = new ExchangeCommand("exchange THB SGD 1.0");
            assertDoesNotThrow(() -> cmd.parseAmount());
        } catch (Exception e) {
            fail();
        }
    }
}
