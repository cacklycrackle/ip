package jasper.command;

import jasper.JasperException;
import jasper.task.Task;
import jasper.task.TaskList;

/**
 * Represents a command to mark a task as not completed.
 */
public class UnmarkCommand implements Command {
    /** 0-based index of the task to be marked */
    private final int index;

    /**
     * Constructs an UnmarkCommand by parsing the task index.
     *
     * @param arg The argument string containing the 1-based index of the task.
     * @throws JasperException If the index is not a valid integer.
     */
    public UnmarkCommand(String arg) throws JasperException {
        try {
            index = Integer.parseInt(arg) - 1;
        } catch (NumberFormatException e) {
            throw new JasperException("Usage: unmark N (integer task index)");
        }
    }

    @Override
    public String execute(TaskList tasks) throws JasperException {
        Task t = tasks.unmark(index);
        return "Get to work... I've marked this task as not done yet\n  " + t;

    }
}
