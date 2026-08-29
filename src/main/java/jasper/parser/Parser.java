package jasper.parser;

import jasper.JasperException;
import jasper.command.ByeCommand;
import jasper.command.Command;
import jasper.command.DeadlineCommand;
import jasper.command.DeleteCommand;
import jasper.command.EventCommand;
import jasper.command.ListCommand;
import jasper.command.MarkCommand;
import jasper.command.TodoCommand;
import jasper.command.UnmarkCommand;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import jasper.command.FindCommand;

public class Parser {
    public static Command parseCmd(String line) throws JasperException {
        String[] tokens = line.strip().split("\\s+", 2);
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
            case "find" -> new FindCommand(arg);
            default -> throw new JasperException("Unknown command: " + tokens[0]);
        };
    }

    public static LocalDateTime parseDateTime(String dt) throws JasperException {
        String pattern = "uuuu-MM-dd HH:mm";
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern)
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            return LocalDateTime.parse(dt, fmt);
        } catch (DateTimeParseException e) {
            throw new JasperException("Accepted datetime format(s): " + pattern);
        }
    }
}
