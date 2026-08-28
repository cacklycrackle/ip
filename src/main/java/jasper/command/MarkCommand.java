package jasper.command;

import jasper.JasperException;
import jasper.task.Task;
import jasper.task.TaskList;

/**
 * Represents a command to mark a task as completed.
 */
public class MarkCommand implements Command {
    /** 0-based index of the task to be marked */
    private final int index;

    /**
     * Constructs a MarkCommand by parsing the task index.
     *
     * @param arg The argument string containing the 1-based index of the task.
     * @throws JasperException If the index is not a valid integer.
     */
    public MarkCommand(String arg) throws JasperException {
        try {
            index = Integer.parseInt(arg) - 1;
        } catch (NumberFormatException e) {
            throw new JasperException("Usage: mark N (integer task index)");
        }
    }

    @Override
    public String execute(TaskList tasks) throws JasperException {
        Task t = tasks.mark(index);
        return "Alright! I've marked this task as done\n  " + t;

    }
}
