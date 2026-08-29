package jasper.ui;

import java.util.Scanner;

/**
 * Handles the user interface interactions including reading input and displaying output.
 */
public class Ui {
    /** Separator line used to divide UI segments */
    private static final String SEPARATOR = "-".repeat(60).indent(4);
    /** ANSI escape code for red text */
    private static final String ANSI_RED = "\u001B[31m";
    /** ANSI escape code to reset text formatting */
    private static final String ANSI_RESET = "\u001B[0m";

    /** Scanner used to read user input from standard input */
    private final Scanner scanner;

    /**
     * Constructs a UI instance and initializes the scanner.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Closes the scanner to release system resources.
     */
    public void close() {
        scanner.close();
    }

    /**
     * Reads the next command line from the user.
     */
    public String readCommand() {
        return scanner.nextLine().strip();
    }

    /**
     * Displays an error message formatted with red text.
     */
    public void showError(String errMessage) {
        String s = ANSI_RED + "Something is amiss... " + ANSI_RESET + errMessage;
        showResponse(s);
    }

    /**
     * Prints the predefined separator line to the console.
     */
    public void showLine() {
        System.out.print(SEPARATOR);
    }

    /**
     * Displays a formatted response message to the user.
     */
    public void showResponse(String message) {
        System.out.print(message.indent(4));
    }

    /**
     * Displays the welcome message and a list of supported commands on startup.
     */
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
