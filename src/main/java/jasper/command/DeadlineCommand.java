package jasper.command;

import jasper.JasperException;
import jasper.parser.Parser;
import jasper.task.Deadline;
import jasper.task.Task;
import jasper.task.TaskList;

public class DeadlineCommand implements Command {
    private final Task t;

    public DeadlineCommand(String arg) throws JasperException {
        int sep = arg.lastIndexOf("/by");
        if (sep == -1) {
            throw new JasperException("Usage: deadline <task> /by <datetime>");
        }
        String task = arg.substring(0, sep).strip();
        String dt = arg.substring(sep + 3).strip();
        if (task.isEmpty() || dt.isEmpty()) {
            throw new JasperException("Usage: deadline <task> /by <datetime>");
        }
        t = new Deadline(task, Parser.parseDateTime(dt));
    }

    @Override
    public String execute(TaskList tasks) {
        tasks.add(t);
        return "Aye, aye. I've added this task:\n  " + t;
    }
}
