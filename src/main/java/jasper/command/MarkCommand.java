package jasper.command;

import jasper.JasperException;
import jasper.task.Task;
import jasper.task.TaskList;

public class MarkCommand implements Command {
    private final int n;

    public MarkCommand(String arg) throws JasperException {
        try {
            this.n = Integer.parseInt(arg) - 1;
        } catch (NumberFormatException e) {
            throw new JasperException("Usage: mark N (integer task index)");
        }
    }

    @Override
    public String execute(TaskList tasks) throws JasperException {
        Task t = tasks.mark(n);
        return "Alright! I've marked this task as done\n  " + t;

    }
}
