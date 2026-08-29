package jasper.command;

import jasper.JasperException;
import jasper.task.TaskList;

/**
 * Represents a command to find tasks containing a specific search phrase.
 */
public class FindCommand implements Command {
    /** Search phrase used to match tasks */
    private final String phrase;

    /**
     * Constructs a FindCommand and validates the search phrase.
     *
     * @param arg The argument string containing the search phrase.
     * @throws JasperException If the argument string is empty.
     */
    public FindCommand(String arg) throws JasperException {
        if (arg.isEmpty()) {
            throw new JasperException("Usage: find <search-phrase>");
        }
        phrase = arg;
    }

    @Override
    public String execute(TaskList tasks) {
        if (tasks.isEmpty()) {
            return "No tasks here! Add some to search through.";
        }
        String response = tasks.find(phrase);
        if (response.isEmpty()) {
            return "Where might the matching tasks be?";
        }
        return "Matching tasks, here you go:\n" + tasks.find(phrase);
    }
}
