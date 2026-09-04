package jasper.command;

import jasper.JasperException;
import jasper.parser.Parser;
import jasper.task.Deadline;
import jasper.task.Task;
import jasper.task.TaskList;

/**
 * Represents a command to add a deadline task.
 */
public class DeadlineCommand implements Command {
    /** Deadline task to be added */
    private final Task task;

    /**
     * Constructs a DeadlineCommand by parsing the provided arguments.
     *
     * @param arg The argument string containing the task description and deadline datetime.
     * @throws JasperException If the argument format is invalid.
     */
    public DeadlineCommand(String arg) throws JasperException {
        int sep = arg.lastIndexOf("/by");
        if (sep == -1) {
            throw new JasperException("Usage: deadline <task> /by <datetime>");
        }
        String description = arg.substring(0, sep).strip();
        String datetime = arg.substring(sep + 3).strip();
        if (description.isEmpty() || datetime.isEmpty()) {
            throw new JasperException("Usage: deadline <task> /by <datetime>");
        }
        task = new Deadline(description, Parser.parseDateTime(datetime));
    }

    @Override
    public CommandResult execute(TaskList tasks) {
        tasks.add(task);
        return new CommandResult(CommandType.DEADLINE, "Aye, aye. I've added this task:\n  " + task);
    }
}
