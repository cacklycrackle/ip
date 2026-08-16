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
        String bye = "Farewell. Hope to see you again soon!".indent(4);
        String lineSeparator = "-".repeat(60).indent(4);

        System.out.print(lineSeparator);
        System.out.print(banner);
        System.out.print(hello);

        // Chat loop
        Task[] tasks = new Task[100];
        int taskIndex = 0;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(lineSeparator);
            String line = sc.nextLine().strip();
            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")) {
                for (int i = 0; i < taskIndex; ++i) {
                    System.out.print(tasks[i].toString().indent(4));
                }
            } else if (line.startsWith("unmark") || line.startsWith("mark")) {
                String[] parts = line.split(" ");
                int taskNum = Integer.parseInt(parts[1]) - 1;
                String output;
                if (parts[0].equals("mark")) {
                    output = "Alright! I've marked this task as done";
                    tasks[taskNum].markAsDone();
                } else { // parts[0].equals("unmark")
                    output = "Get to work... I've marked this tasks as not done yet";
                    tasks[taskNum].markAsUndone();
                }
                System.out.print(String.format("%s:\n  %s", output, tasks[taskNum]).indent(4));
            } else if (taskIndex >= tasks.length) {
                System.out.print("ERROR: No more space in list! Time to exit.".indent(4));
            } else {
                tasks[taskIndex++] = new Task(line);
                System.out.print(String.format("added %s", line).indent(4));
            }
        }
        sc.close();

        System.out.print(bye);
        System.out.print(lineSeparator);
    }
}
