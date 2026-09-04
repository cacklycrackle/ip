package jasper;

import jasper.command.Command;
import jasper.command.CommandResult;
import jasper.command.CommandType;
import jasper.parser.Parser;
import jasper.storage.Storage;
import jasper.task.TaskList;

/**
 * Represents the main application class that initializes and coordinates the application components.
 */
public class Jasper {
    /** Storage component handling data persistence */
    private final Storage storage;
    /** List component managing user tasks */
    private final TaskList tasks;

    /**
     * Constructs a Jasper instance and initializes core application components.
     *
     * @param parent Parent directory path for the storage file.
     * @param filename Name of the storage file.
     */
    public Jasper(String parent, String filename) {
        storage = new Storage(parent, filename);
        TaskList tmp;
        try {
            tmp = new TaskList(storage.load());
        } catch (JasperException e) {
            tmp = new TaskList();
        }
        tasks = tmp;
    }

    /**
     * Returns the result of parsing and executing the user input command.
     *
     * @param input String input provided by the user.
     * @return Result containing the command type and execution response.
     */
    public CommandResult getResult(String input) {
        try {
            Command c = Parser.parseCmd(input);
            CommandResult result = c.execute(tasks);
            storage.save(tasks);
            return result;
        } catch (JasperException e) {
            return new CommandResult(CommandType.ERROR, "Error: " + e.getMessage());
        }
    }
}
