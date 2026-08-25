package jasper;

import jasper.command.Command;
import jasper.parser.Parser;
import jasper.storage.Storage;
import jasper.task.TaskList;
import jasper.ui.Ui;

public class Jasper {
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

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

    private void run() {
        ui.showWelcome();
        boolean toQuit = false;
        while (!toQuit) {
            String response;
            try {
                Command cmd = Parser.parseCmd(ui.readCommand());
                response = cmd.execute(tasks);
                toQuit = cmd.isQuit();
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

    public static void main(String[] args) {
        new Jasper("data", "jasper.txt").run();
    }
}
