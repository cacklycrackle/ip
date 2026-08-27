package jasper.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import jasper.JasperException;

public class TaskListTest {
    @Test
    public void delete_validIndex_success() {
        Task stub = new TaskStub();
        TaskList tasks = new TaskList(List.of(stub));
        try {
            Task result = tasks.delete(0);
            assertSame(stub, result);
            assertEquals(0, tasks.size());
        } catch (JasperException e) {
            fail();
        }
    }

    @Test
    public void delete_invalidIndices_exceptionThrown() {
        TaskList tasks = new TaskList(List.of(new TaskStub()));
        try {
            tasks.delete(-1);
            fail();
        } catch (JasperException e) {
            assertEquals("Task index out of range!", e.getMessage());
        }
        try {
            tasks.delete(1);
            fail();
        } catch (JasperException e) {
            assertEquals("Task index out of range!", e.getMessage());
        }
    }

    @Test
    public void mark_validIndex_success() {
        Task stub = new TaskStub();
        TaskList tasks = new TaskList(List.of(stub));
        try {
            Task result = tasks.mark(0);
            assertSame(stub, result);
            assertTrue(stub.getDone());
        } catch (JasperException e) {
            fail();
        }
    }


    @Test
    void mark_invalidIndices_exceptionThrown() {
        TaskList tasks = new TaskList(List.of(new TaskStub()));
        try {
            tasks.mark(-1);
            fail();
        } catch (JasperException e) {
            assertEquals("Task index out of range!", e.getMessage());
        }
        try {
            tasks.mark(1);
            fail();
        } catch (JasperException e) {
            assertEquals("Task index out of range!", e.getMessage());
        }
    }

    @Test
    public void unmark_validIndex_success() {
        Task stub = new TaskStub();
        stub.markDone();
        TaskList tasks = new TaskList(List.of(stub));
        try {
            Task result = tasks.unmark(0);
            assertSame(stub, result);
            assertFalse(stub.getDone());
        } catch (JasperException e) {
            fail();
        }
    }

    @Test
    public void unmark_invalidIndices_exceptionThrown() {
        Task stub = new TaskStub();
        stub.markDone();
        TaskList tasks = new TaskList(List.of(stub));
        try {
            tasks.mark(-1);
            fail();
        } catch (JasperException e) {
            assertEquals("Task index out of range!", e.getMessage());
        }
        try {
            tasks.mark(1);
            fail();
        } catch (JasperException e) {
            assertEquals("Task index out of range!", e.getMessage());
        }
    }
}
