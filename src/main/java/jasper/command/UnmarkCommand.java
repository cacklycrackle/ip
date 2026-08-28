package jasper.command;

import jasper.JasperException;
import jasper.task.Task;
import jasper.task.TaskList;

public class UnmarkCommand implements Command {
    private final int index;

    public UnmarkCommand(String arg) throws JasperException {
        try {
            index = Integer.parseInt(arg) - 1;
        } catch (NumberFormatException e) {
            throw new JasperException("Usage: unmark N (integer task index)");
        }
    }

    @Override
    public String execute(TaskList tasks) throws JasperException {
        Task t = tasks.unmark(index);
        return "Get to work... I've marked this task as not done yet\n  " + t;

    }
}
