package jasper.command;

import jasper.JasperException;
import jasper.task.Task;
import jasper.task.TaskList;
import jasper.task.Todo;

public class TodoCommand implements Command {
    private final Task task;

    public TodoCommand(String arg) throws JasperException {
        if (arg.isEmpty()) {
            throw new JasperException("Usage: todo <task>");
        }
        task = new Todo(arg);
    }

    @Override
    public String execute(TaskList tasks) {
        tasks.add(task);
        return "Aye, aye. I've added this task:\n  " + task;
    }
}
