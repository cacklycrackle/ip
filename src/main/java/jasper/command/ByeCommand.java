package jasper.command;

import jasper.JasperException;
import jasper.task.TaskList;

/**
 * Represents a command to exit the application.
 */
public class ByeCommand implements Command {
    /**
     * Constructs a ByeCommand and validates the argument.
     *
     * @param arg The argument string which must be empty.
     * @throws JasperException If the argument string is not empty.
     */
    public ByeCommand(String arg) throws JasperException {
        if (!arg.isEmpty()) {
            throw new JasperException("Usage: bye");
        }
    }

    @Override
    public String execute(TaskList tasks) {
        return "Farewell. Hope to see you again soon!";
    }

    @Override
    public boolean isQuit() {
        return true;
    }
}
