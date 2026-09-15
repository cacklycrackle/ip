package jasper.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import jasper.JasperException;
import jasper.task.TaskList;
import jasper.task.TaskStub;

public class MarkCommandTest {
    @Test
    public void constructor_singleIntegerArgument_success() throws JasperException {
        MarkCommand cmd = new MarkCommand("10");
        assertNotNull(cmd);
    }

    @Test
    public void constructor_rangeArgument_success() throws JasperException {
        MarkCommand cmd = new MarkCommand("1..3");
        assertNotNull(cmd);
    }

    @Test
    public void constructor_nonIntegerArgument_exceptionThrown() {
        assertThrows(JasperException.class, () -> new MarkCommand("one"));
    }

    @Test
    public void constructor_tooManyPartsArgument_exceptionThrown() {
        assertThrows(JasperException.class, () -> new MarkCommand("1..2..3"));
    }

    @Test
    public void constructor_startExceedsStop_exceptionThrown() {
        JasperException e = assertThrows(JasperException.class, () -> new MarkCommand("5..2"));
        assertEquals("Start index must be at most stop index!", e.getMessage());
    }

    @Test
    public void execute_validSingleTaskIndex_success() throws JasperException {
        TaskStub stub = new TaskStub();
        TaskList tasks = new TaskList(List.of(stub));
        MarkCommand cmd = new MarkCommand("1");
        cmd.execute(tasks);

        assertTrue(stub.isDone());
    }

    @Test
    public void execute_validRangeTaskIndex_success() throws JasperException {
        TaskStub stub1 = new TaskStub();
        TaskStub stub2 = new TaskStub();
        TaskList tasks = new TaskList(List.of(stub1, stub2));
        MarkCommand cmd = new MarkCommand("1..2");
        cmd.execute(tasks);

        assertTrue(stub1.isDone());
        assertTrue(stub2.isDone());
    }

    @Test
    public void execute_invalidTaskIndex_exceptionThrown() {
        TaskList tasks = new TaskList();
        JasperException e = assertThrows(JasperException.class, () -> new MarkCommand("1").execute(tasks));
        assertEquals("Task index out of range!", e.getMessage());
    }
}
