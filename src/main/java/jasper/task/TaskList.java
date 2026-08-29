package jasper.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jasper.JasperException;

public class TaskList implements Iterable<Task> {
    private final List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public TaskList() {
        this(new ArrayList<>(100));
    }

    private static void check(int index, List<Task> tasks) throws JasperException {
        if (index < 0 || index >= tasks.size()) {
            throw new JasperException("Task index out of range!");
        }
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public int size() {
        return tasks.size();
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public Task delete(int n) throws JasperException {
        check(n, tasks);
        return tasks.remove(n);
    }

    public Task mark(int n) throws JasperException {
        check(n, tasks);
        Task t = tasks.get(n);
        t.markDone();
        return t;
    }

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
