package jasper.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import jasper.JasperException;
import jasper.task.TaskList;
import jasper.task.TaskStub;

public class UnmarkCommandTest {
    @Test
    public void constructor_integerArgument_success() {
        try {
            UnmarkCommand cmd = new UnmarkCommand("10");
            assertNotNull(cmd);
        } catch (JasperException e) {
            fail();
        }
    }

    @Test
    public void constructor_nonIntegerArgument_exceptionThrown() {
        try {
            new UnmarkCommand("one");
        } catch (JasperException e) {
            assertEquals("Usage: unmark N (integer task index)", e.getMessage());
        }
    }

    @Test
    public void execute_validTaskIndex_success() {
        TaskStub stub = new TaskStub();
        TaskList tasks = new TaskList(List.of(stub));
        try {
            UnmarkCommand cmd = new UnmarkCommand("1");
            CommandResult result = cmd.execute(tasks);
            assertEquals("Get to work... I've marked this task as not done yet\n  [ ] sample_task",
                    result.response());
            assertFalse(stub.isDone());
        } catch (JasperException e) {
            fail();
        }
    }

    @Test
    public void execute_invalidTaskIndex_exceptionThrown() {
        TaskList tasks = new TaskList();
        try {
            new UnmarkCommand("1").execute(tasks);
            fail();
        } catch (JasperException e) {
            assertEquals("Task index out of range!", e.getMessage());
        }
    }
}
