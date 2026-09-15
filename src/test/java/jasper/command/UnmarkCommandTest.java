package jasper.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import jasper.JasperException;
import jasper.task.TaskList;
import jasper.task.TaskStub;

public class UnmarkCommandTest {
    @Test
    public void constructor_singleIntegerArgument_success() throws JasperException {
        UnmarkCommand cmd = new UnmarkCommand("10");
        assertNotNull(cmd);
    }

    @Test
    public void constructor_rangeArgument_success() throws JasperException {
        UnmarkCommand cmd = new UnmarkCommand("2..5");
        assertNotNull(cmd);
    }

    @Test
    public void constructor_nonIntegerArgument_exceptionThrown() {
        assertThrows(JasperException.class, () -> new UnmarkCommand("one"));
    }

    @Test
    public void constructor_tooManyPartsArgument_exceptionThrown() {
        assertThrows(JasperException.class, () -> new UnmarkCommand("1..2..3"));
    }

    @Test
    public void constructor_startExceedsStop_exceptionThrown() {
        JasperException e = assertThrows(JasperException.class, () -> new UnmarkCommand("5..2"));
        assertEquals("Start index cannot exceed stop index!", e.getMessage());
    }

    @Test
    public void execute_validSingleTaskIndex_success() throws JasperException {
        TaskStub stub = new TaskStub();
        TaskList tasks = new TaskList(List.of(stub));
        UnmarkCommand cmd = new UnmarkCommand("1");
        cmd.execute(tasks);

        assertFalse(stub.isDone());
    }

    @Test
    public void execute_validRangeTaskIndex_success() throws JasperException {
        TaskStub stub1 = new TaskStub();
        TaskStub stub2 = new TaskStub();
        TaskList tasks = new TaskList(List.of(stub1, stub2));
        UnmarkCommand cmd = new UnmarkCommand("1..2"); // assume stubs start unmarked but should still be processed
        cmd.execute(tasks);

        assertFalse(stub1.isDone());
        assertFalse(stub2.isDone());
    }

    @Test
    public void execute_invalidTaskIndex_exceptionThrown() {
        TaskList tasks = new TaskList();
        JasperException e = assertThrows(JasperException.class, () -> new UnmarkCommand("1").execute(tasks));
        assertEquals("Task index out of range!", e.getMessage());
    }
}
