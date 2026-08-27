package jasper.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import jasper.JasperException;

public class ByeCommandTest {
    @Test
    public void constructor_emptyArgument_success() {
        try {
            ByeCommand cmd = new ByeCommand("");
            assertNotNull(cmd);
        } catch (JasperException e) {
            fail();
        }
    }
    @Test
    public void constructor_nonemptyArgument_exceptionThrown() {
        try {
            new ByeCommand("extra");
            fail();
        } catch (JasperException e) {
            assertEquals("Usage: bye", e.getMessage());
        }
    }

    @Test
    public void isQuit_emptyArgument_returnsTrue() {
        try {
            assertTrue(new ByeCommand("").isQuit());
        } catch (JasperException e) {
            fail();
        }
    }
}
