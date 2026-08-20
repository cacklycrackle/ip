import java.util.ArrayList;
import java.util.List;
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
                Hello! I'm Jasper.
                What can I do for you? End session with 'bye'.
                """.indent(4);
        String lineSeparator = "-".repeat(60).indent(4);

        // display welcome
        System.out.print(lineSeparator);
        System.out.print(banner);
        System.out.print(hello);
        System.out.print(lineSeparator);

        // chat loop
        List<Task> tasks = new ArrayList<>(100);
        Scanner sc = new Scanner(System.in);
        boolean toQuit = false;
        while (!toQuit) {
            // parse command
            String response;
            try {
                Command cmd = parse(sc.nextLine());
                response = cmd.execute(tasks);
                toQuit = cmd.isQuit();
            } catch (JasperException e) {
                response = "Something is amiss... " + e.getMessage();
            }
            // display chat response
            System.out.print(response.indent(4));
            System.out.print(lineSeparator);
        }
        sc.close();
    }

    private static Command parse(String line) throws JasperException {
        String[] tokens = line.strip().split("\\s+", 2);
        String arg = (tokens.length > 1) ? tokens[1] : "";
        return switch (tokens[0]) {
            case "bye" -> new ByeCommand(arg);
            case "list" -> new ListCommand(arg);
            case "unmark" -> new UnmarkCommand(arg);
            case "mark" -> new MarkCommand(arg);
            case "todo" -> new TodoCommand(arg);
            case "deadline" -> new DeadlineCommand(arg);
            case "event" -> new EventCommand(arg);
            default -> throw new JasperException("Unknown command: " + tokens[0]);
        };
    }
}
