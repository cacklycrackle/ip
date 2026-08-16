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
        String[] tasks = new String[100];
        boolean[] isTaskDone = new boolean[tasks.length];
        int taskIndex = 0;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(lineSeparator);
            String line = sc.nextLine().strip();
            if (line.equals("bye")) {
                break;
            } else if (line.equals("list")) {
                for (int i = 0; i < taskIndex; ++i) {
                    String entry = String.format("%d.[%c] %s\n",
                            i + 1, isTaskDone[i] ? ' ' : 'X', tasks[i]);
                    System.out.print(entry.indent(4));
                }
            } else if (line.startsWith("mark")) {
                String[] parts = line.split(" ");
                int taskNum = Integer.parseInt(parts[1]) - 1;
                String output = String.format("""
                        Alright! I've marked this task as done:
                          [%c] %s
                        """,
                        isTaskDone[taskNum] ? ' ' : 'X', tasks[taskNum]);
                isTaskDone[taskNum] = true;
                System.out.print(output.indent(4));
            } else if (taskIndex >= tasks.length) {
                System.out.print("ERROR: No more space in list! Time to exit.".indent(4));
            } else {
                tasks[taskIndex++] = line;
                line = "added " + line;
                System.out.print(line.indent(4));
            }
        }
        sc.close();

        System.out.print(bye);
        System.out.print(lineSeparator);
    }
}
