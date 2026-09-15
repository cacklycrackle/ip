package jasper.command;

import jasper.JasperException;
import jasper.task.Task;
import jasper.task.TaskList;

/**
 * Represents a command to mark a task(s) as not completed.
 */
public class UnmarkCommand implements Command {
    /** Valid command format message */
    private static final String USAGE_MSG = """
            Usage (with positive integers indices):
                for single task:    unmark N (single task)
                for multiple tasks: unmark start..stop (both inclusive, start <= stop)
            """;
    /** 0-based first index of tasks in range to be marked as undone */
    private final int startIndex;
    /** 0-based last index of tasks in range to be marked as undone */
    private final int stopIndex;

    /**
     * Constructs an UnmarkCommand by parsing the task index(es).
     *
     * @param arg The argument string containing the 1-based task index(es).
     * @throws JasperException If the index is not a valid integer.
     */
    public UnmarkCommand(String arg) throws JasperException {
        String[] parts = arg.split("\\.\\.");
        if (parts.length > 2) {
            throw new JasperException(USAGE_MSG);
        }
        try {
            startIndex = Integer.parseInt(parts[0]) - 1;
            stopIndex = (parts.length == 1)
                    ? startIndex
                    : Integer.parseInt(parts[1]) - 1;
            if (startIndex > stopIndex) {
                throw new JasperException("Start index cannot exceed stop index!");
            }
        } catch (NumberFormatException e) {
            throw new JasperException(USAGE_MSG);
        }
    }

    @Override
    public CommandResult execute(TaskList tasks) throws JasperException {
        StringBuilder sb = new StringBuilder("Get to work... I've marked these tasks as not done yet:");
        for (int i = startIndex; i <= stopIndex; ++i) {
            Task t = tasks.unmark(i);
            sb.append("\n  ").append(i + 1).append(". ").append(t);
        }
        return new CommandResult(CommandType.UNMARK, sb.toString());
    }
}
