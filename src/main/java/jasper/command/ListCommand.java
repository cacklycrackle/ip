package jasper.command;

import jasper.JasperException;
import jasper.task.TaskList;

public class ListCommand implements Command {
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
