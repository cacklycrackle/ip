package jasper.command;

import jasper.JasperException;
import jasper.parser.Parser;
import jasper.task.Event;
import jasper.task.Task;
import jasper.task.TaskList;

/**
 * Represents a command to add an event task.
 */
public class EventCommand implements Command {
    /** Event task to be added */
    private final Task task;

    /**
     * Constructs an EventCommand by parsing the task description and timeframe.
     *
     * @param arg The argument string containing the task details, start datetime, and end datetime.
     * @throws JasperException If the argument format is invalid.
     */
    public EventCommand(String arg) throws JasperException {
        int sepFrom = arg.lastIndexOf("/from");
        int sepTo = arg.lastIndexOf("/to");
        if (sepFrom == -1 || sepTo == -1 || sepTo < sepFrom) {
            throw new JasperException("Usage: event <task> /from <datetime> /to <datetime>");
        }
        String description = arg.substring(0, sepFrom).strip();
        String datetimeFrom = arg.substring(sepFrom + 5, sepTo).strip();
        String datetimeTo = arg.substring(sepTo + 3).strip();
        if (description.isEmpty() || datetimeFrom.isEmpty() || datetimeTo.isEmpty()) {
            throw new JasperException("Usage: event <task> /from <datetime> /to <datetime>");
        }
        task = new Event(description, Parser.parseDateTime(datetimeFrom), Parser.parseDateTime(datetimeTo));
    }

    @Override
    public CommandResult execute(TaskList tasks) {
        tasks.add(task);
        return new CommandResult(CommandType.EVENT, "Aye, aye. I've added this task:\n  " + task);
    }
}
