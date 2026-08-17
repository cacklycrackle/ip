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
        Task[] tasks = new Task[100];
        int taskIndex = 0;
        Scanner sc = new Scanner(System.in);
        boolean quit = false;
        while (!quit) {
            // parse command
            String[] tokens = sc.nextLine().strip().split("\\s+", 2);
            String response = switch (tokens[0]) {
                case "bye" -> {
                    quit = true;
                    yield "Farewell. Hope to see you again soon!";
                }
                case "list" -> {
                    if (taskIndex == 0) {
                        yield "No tasks here! Add some to track.";
                    }
                    StringBuilder sb = new StringBuilder("Here are your tasks:\n");
                    for (int i = 0; i < taskIndex; ++i) {
                        sb.append(String.format("%d.%s", i + 1, tasks[i])).append('\n');
                    }
                    yield sb.toString();
                }
                case "unmark" -> {
                    int n = Integer.parseInt(tokens[1]) - 1; // task number in list
                    tasks[n].markAsUndone();
                    yield "Get to work... I've marked this task as not done yet\n  " + tasks[n];
                }
                case "mark" -> {
                    int n = Integer.parseInt(tokens[1]) - 1; // task number in list
                    tasks[n].markAsDone();
                    yield "Alright! I've marked this task as done\n  " + tasks[n];
                }
                case "todo" -> {
                    Task t = new Todo(tokens[1]);
                    tasks[taskIndex++] = t;
                    yield "Aye, aye. I've added this task:\n  " + t;
                }
                case "deadline" -> {
                    String[] parts = tokens[1].split("\\s+/by\\s+", 2);
                    Task t = new Deadline(parts[0].strip(), parts[1].strip());
                    tasks[taskIndex++] = t;
                    yield "Aye, aye. I've added this task:\n  " + t;
                }
                case "event" -> {
                    String[] parts = tokens[1].split("\\s+/(from|to)\\s+");
                    Task t = new Event(parts[0].strip(), parts[1].strip(), parts[2].strip());
                    tasks[taskIndex++] = t;
                    yield "Aye, aye. I've added this task:\n  " + t;
                }
                default -> "ERROR: unknown command";
            };
            // display chat response
            System.out.print(response.indent(4));
            System.out.print(lineSeparator);
        }
        sc.close();
    }
}
