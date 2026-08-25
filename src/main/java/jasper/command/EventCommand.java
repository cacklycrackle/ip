package jasper.command;

import jasper.JasperException;
import jasper.parser.Parser;
import jasper.task.Event;
import jasper.task.Task;
import jasper.task.TaskList;

public class EventCommand implements Command {
    private final Task t;

    public EventCommand(String arg) throws JasperException {
        String[] parts = arg.split("\\s+/(from|to)\\s+", 3);
        if (parts.length != 3) {
            throw new JasperException("Usage: event <task> /from <datetime> /to <datetime>");
        }
        t = new Event(parts[0], Parser.parseDateTime(parts[1]), Parser.parseDateTime(parts[2]));
    }

    @Override
    public String execute(TaskList tasks) {
        tasks.add(t);
        return "Aye, aye. I've added this task:\n  " + t;
    }
}
