package jasper.command;

import jasper.JasperException;
import jasper.task.TaskList;

/**
 * Represents an executable command in the application.
 */
public interface Command {
    /**
     * Executes the command using the provided task list.
     *
     * @param tasks List of tasks to operate on.
     * @return Response string generated after execution.
     * @throws JasperException If an error occurs during execution.
     */
    public String execute(TaskList tasks) throws JasperException;

    /**
     * Returns whether this command should terminate the application.
     *
     * @return True if the application should quit, or false otherwise.
     */
    public default boolean isQuit() {
        return false;
    }
}