package jasper.command;

import jasper.JasperException;
import jasper.task.TaskList;

/**
 * Represents a command to list all tasks.
 */
public class ListCommand implements Command {
    /**
     * Constructs a ListCommand and validates the argument.
     *
     * @param arg The argument string which must be empty.
     * @throws JasperException If the argument string is not empty.
     */
    public ListCommand(String arg) throws JasperException {
        if (!arg.isEmpty()) {
            throw new JasperException("Usage: list");
        }
    }

    @Override
    public String execute(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "No tasks here! Add some to track.";
        }
        return "Here are your tasks:\n" + tasks;
    }
}
