package jasper.ui;

import java.util.Scanner;

public class Ui {
    private static final String SEPARATOR = "-".repeat(60).indent(4);
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_RESET = "\u001B[0m";

    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void close() {
        scanner.close();
    }

    public String readCommand() {
        return scanner.nextLine().strip();
    }

    public void showError(String errMessage) {
        String s = ANSI_RED + "Something is amiss... " + ANSI_RESET + errMessage;
        showResponse(s);
    }

    public void showLine() {
        System.out.print(SEPARATOR);
    }

    public void showResponse(String message) {
        System.out.print(message.indent(4));
    }

    public void showWelcome() {
        showLine();
        System.out.print("""
                    _                                \s
                 _ | |  __ _   ___  _ __   ___   _ _ \s
                | || | / _` | (_-< | '_ \\ / -_) | '_|\s
                 \\__/  \\__,_| /__/ | .__/ \\___| |_|  \s
                                   |_|               \s
                """.indent(4)
        );
        System.out.print("""
                Hello! I'm Jasper. Whatever shalt thou require of me today?
                Supported commands:
                    bye, list, find, unmark, mark, delete, todo, deadline, event
                """.indent(4)
        );
        showLine();
    }
}
