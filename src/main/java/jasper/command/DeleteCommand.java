package jasper.command;

import jasper.JasperException;
import jasper.task.Task;
import jasper.task.TaskList;

/**
 * Represents a command to delete a task from the list of tasks.
 */
public class DeleteCommand implements Command {
    /** 0-based index of the task to be deleted */
    private final int index;

    /**
     * Constructs a DeleteCommand by parsing the task index.
     *
     * @param arg The argument string containing the 1-based index of the task.
     * @throws JasperException If the index is not a valid integer.
     */
    public DeleteCommand(String arg) throws JasperException {
        try {
            index = Integer.parseInt(arg) - 1;
        } catch (NumberFormatException e) {
            throw new JasperException("Usage: delete N (integer task index)");
        }
    }

    @Override
    public String execute(TaskList tasks) throws JasperException {
        Task t = tasks.delete(index);
        return "This task shall be terminated, if you insist:\n  " + t
                + "\n1 task down, " + tasks.size() + " to go.";

    }
}
