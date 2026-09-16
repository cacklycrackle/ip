package jasper.command;

import jasper.JasperException;
import jasper.task.Task;
import jasper.task.TaskList;

/**
 * Represents a command to delete a task(s) from the list of tasks.
 */
public class DeleteCommand implements Command {
    /** Valid command format message */
    private static final String USAGE_MSG = """
            Usage (with positive integers indices):
                delete N (single task)
                delete start..stop (multiple tasks, both indices inclusive, start <= stop)
            """;
    /** 0-based first index of tasks in range to be deleted */
    private final int startIndex;
    /** 0-based last index of tasks in range to be deleted */
    private final int stopIndex;

    /**
     * Constructs a DeleteCommand by parsing the task index(es).
     *
     * @param arg The argument string containing the 1-based task index(es).
     * @throws JasperException If the index is not a valid integer.
     */
    public DeleteCommand(String arg) throws JasperException {
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
        StringBuilder sb = new StringBuilder("These shall be terminated, if you insist:");
        Task[] buf = new Task[stopIndex - startIndex + 1];
        for (int i = buf.length - 1; i >= 0; --i) {
            buf[i] = tasks.delete(startIndex + i);
        }
        for (int i = 0; i < buf.length; ++i) {
            sb.append("\n  ").append(startIndex + i + 1).append(". ").append(buf[i]);
        }
        sb.append('\n').append(String.format("%d tasks down, %d to go.", buf.length, tasks.getCount()));
        return new CommandResult(CommandType.DELETE, sb.toString());
    }
}
