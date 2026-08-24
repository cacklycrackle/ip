import java.util.Scanner;

public class Jasper {
    public static void main(String[] args) {
        String banner = """
                    _                                \s
                 _ | |  __ _   ___  _ __   ___   _ _ \s
                | || | / _` | (_-< | '_ \\ / -_) | '_|\s
                 \\__/  \\__,_| /__/ | .__/ \\___| |_|  \s
                                   |_|               \s
                """.indent(4);
        String hello = """
                Hello! I'm Jasper. Whatever shalt thou require of me today?
                Supported commands:
                    bye, list, unmark, mark, delete, todo, deadline, event
                """.indent(4);
        String lineSeparator = "-".repeat(60).indent(4);

        // display welcome
        System.out.print(lineSeparator);
        System.out.print(banner);
        System.out.print(hello);
        System.out.print(lineSeparator);

        // load tasks from disk
        Storage storage = new Storage("data", "jasper.txt");
        TaskList tasks;
        try {
            tasks = new TaskList(storage.load());
        } catch (JasperException e) {
            System.out.println(e.getMessage().indent(4)) ;
            tasks = new TaskList();
        }

        // chat loop
        Scanner sc = new Scanner(System.in);
        boolean toQuit = false;
        while (!toQuit) {
            // parse command
            String response;
            try {
                String[] tokens = sc.nextLine().strip().split("\\s+", 2);
                Command cmd = parse(tokens);
                response = cmd.execute(tasks);
                toQuit = cmd.isQuit();
                // save tasks back to disk if modified
                switch (tokens[0]) {
                    case "unmark", "mark", "delete", "todo", "deadline", "event":
                        storage.save(tasks);
                        // Fallthrough
                    default:
                        break;
                }
            } catch (JasperException e) {
                response = e.getMessage();
            }
            // display chat response
            System.out.print(response.indent(4));
            System.out.print(lineSeparator);
        }
        sc.close();
    }

    private static Command parse(String[] tokens) throws JasperException {
        String arg = (tokens.length > 1) ? tokens[1] : "";
        return switch (tokens[0]) {
            case "bye" -> new ByeCommand(arg);
            case "list" -> new ListCommand(arg);
            case "unmark" -> new UnmarkCommand(arg);
            case "mark" -> new MarkCommand(arg);
            case "delete" -> new DeleteCommand(arg);
            case "todo" -> new TodoCommand(arg);
            case "deadline" -> new DeadlineCommand(arg);
            case "event" -> new EventCommand(arg);
            default -> throw new JasperException("Unknown command: " + tokens[0]);
        };
    }
}
