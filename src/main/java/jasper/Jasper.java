package jasper;

import jasper.command.Command;
import jasper.parser.Parser;
import jasper.storage.Storage;
import jasper.task.TaskList;
import jasper.ui.Ui;

/**
 * Represents the main application class that initializes and coordinates the application components.
 */
public class Jasper {
    /** Storage component handling data persistence */
    private final Storage storage;
    /** List component managing user tasks */
    private final TaskList tasks;
    /** User interface component handling input and output */
    private final Ui ui;

    /**
     * Constructs a Jasper instance and initializes the core application components.
     *
     * @param parent Parent directory path for the storage file.
     * @param filename Name of the storage file.
     */
    private Jasper(String parent, String filename) {
        storage = new Storage(parent, filename);
        TaskList tmp;
        try {
            tmp = new TaskList(storage.load());
        } catch (JasperException e) {
            tmp = new TaskList();
        }
        tasks = tmp;
        ui = new Ui();
    }

    /**
     * Runs the main execution loop to continuously process user commands.
     */
    private void run() {
        ui.showWelcome();
        boolean shouldQuit = false;
        while (!shouldQuit) {
            String response;
            try {
                Command cmd = Parser.parseCmd(ui.readCommand());
                response = cmd.execute(tasks);
                shouldQuit = cmd.isQuit();
                ui.showResponse(response);
                storage.save(tasks);
            } catch (JasperException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        ui.close();
    }

    /**
     * Serves as the main entry point to start the Jasper application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Jasper("data", "jasper.txt").run();
    }
}
