package jasper.command;

import jasper.JasperException;
import jasper.task.TaskList;

public interface Command {
    public String execute(TaskList tasks) throws JasperException;

    public default boolean isQuit() {
        return false;
    }
}