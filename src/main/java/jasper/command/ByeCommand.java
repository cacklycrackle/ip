package jasper.command;

import jasper.JasperException;
import jasper.task.TaskList;

public class ByeCommand implements Command {
    public ByeCommand(String arg) throws JasperException {
        if (!arg.isEmpty()) {
            throw new JasperException("Usage: bye");
        }
    }

    @Override
    public String execute(TaskList tasks) {
        return "Farewell. Hope to see you again soon!";
    }

    @Override
    public boolean isQuit() {
        return true;
    }
}
