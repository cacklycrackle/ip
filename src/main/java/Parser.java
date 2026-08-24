import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
            default -> throw new JasperException("Unknown command: " + tokens[0]);
        };
    }

    public static LocalDateTime parseDateTime(String dt) throws JasperException {
        String pattern = "yyyy-MM-dd HH:mm";
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern);
        try {
            return LocalDateTime.parse(dt, fmt);
        } catch (DateTimeParseException e) {
            throw new JasperException("Follow these datetime format(s): " + pattern);
        }
    }
}
