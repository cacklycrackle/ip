package jasper.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import jasper.JasperException;
import jasper.task.TaskList;
import jasper.task.TaskStub;

public class MarkCommandTest {
    @Test
    public void constructor_integerArgument_success() {
        try {
            MarkCommand cmd = new MarkCommand("10");
            assertNotNull(cmd);
        } catch (JasperException e) {
            fail();
        }
    }

    @Test
    public void constructor_nonIntegerArgument_exceptionThrown() {
        try {
            new MarkCommand("one");
            fail();
        } catch (JasperException e) {
            assertEquals("Usage: mark N (integer task index)", e.getMessage());
        }
    }

    @Test
    public void execute_validTaskIndex_success() {
        TaskStub stub = new TaskStub();
        TaskList tasks = new TaskList(List.of(stub));
        try {
            MarkCommand cmd = new MarkCommand("1");
            CommandResult result = cmd.execute(tasks);
            assertEquals("Alright! I've marked this task as done\n  [X] sample_task", result.response());
            assertTrue(stub.isDone());
        } catch (JasperException e) {
            fail();
        }
    }

    @Test
    public void execute_invalidTaskIndex_exceptionThrown() {
        TaskList tasks = new TaskList();
        try {
            new MarkCommand("1").execute(tasks);
            fail();
        } catch (JasperException e) {
            assertEquals("Task index out of range!", e.getMessage());
        }
    }
}
