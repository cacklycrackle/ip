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
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(lineSeparator);
            String line = sc.nextLine();
            if (line.equals("bye")) {
                break;
            }
            System.out.print(line.indent(4));
        }
        sc.close();

        System.out.print(bye);
        System.out.print(lineSeparator);
    }
}
