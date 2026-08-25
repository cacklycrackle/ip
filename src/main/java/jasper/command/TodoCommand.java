package jasper.command;

import jasper.JasperException;
import jasper.task.Task;
import jasper.task.TaskList;
import jasper.task.Todo;

public class TodoCommand implements Command {
    private final Task t;

    public TodoCommand(String arg) throws JasperException {
        if (arg.isEmpty()) {
            throw new JasperException("Usage: todo <task>");
        }
        t = new Todo(arg);
    }

    @Override
    public String execute(TaskList tasks) {
        tasks.add(t);
        return "Aye, aye. I've added this task:\n  " + t;
    }
}
