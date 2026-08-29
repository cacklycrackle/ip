package jasper.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jasper.JasperException;

/**
 * Represents a collection of tasks and provides operations to manage them.
 */
public class TaskList implements Iterable<Task> {
    /** Internal list storing the tasks */
    private final List<Task> tasks;

    /**
     * Constructs a TaskList initialized with an existing list of tasks.
     *
     * @param tasks Initial list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    /**
     * Constructs an empty TaskList with a default initial capacity.
     */
    public TaskList() {
        this(new ArrayList<>(100));
    }

    /**
     * Checks if the given index is valid within the specified task list.
     *
     * @param index 0-based index to validate.
     * @param tasks List of tasks to check against.
     * @throws JasperException If the index is out of bounds.
     */
    private static void check(int index, List<Task> tasks) throws JasperException {
        if (index < 0 || index >= tasks.size()) {
            throw new JasperException("Task index out of range!");
        }
    }

    /**
     * Returns whether the task list is empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the number of tasks in the list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Adds a new task to the list.
     *
     * @param t Task to be added.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param n 0-based index of the task to delete.
     * @return Task that was removed.
     * @throws JasperException If the index is out of bounds.
     */
    public Task delete(int n) throws JasperException {
        check(n, tasks);
        return tasks.remove(n);
    }

    /**
     * Marks the task at the specified index as completed.
     *
     * @param n 0-based index of the task to mark.
     * @return Task that was marked.
     * @throws JasperException If the index is out of bounds.
     */
    public Task mark(int n) throws JasperException {
        check(n, tasks);
        Task t = tasks.get(n);
        t.markDone();
        return t;
    }

    /**
     * Marks the task at the specified index as not completed.
     *
     * @param n 0-based index of the task to unmark.
     * @return Task that was unmarked.
     * @throws JasperException If the index is out of bounds.
     */
    public Task unmark(int n) throws JasperException {
        check(n, tasks);
        Task t = tasks.get(n);
        t.markUndone();
        return t;
    }

    /**
     * Returns a formatted string containing all tasks that match the given search phrase.
     *
     * @param phrase Search phrase to match against task descriptions.
     * @return Formatted string of all matching tasks.
     */
    public String find(String phrase) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); ++i) {
            Task t = tasks.get(i);
            if (t.getDescription().contains(phrase)) {
                sb.append(i + 1).append(". ").append(t).append('\n');
            }
        }
        return sb.toString();
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); ++i) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append('\n');
        }
        return sb.toString();
    }
}
