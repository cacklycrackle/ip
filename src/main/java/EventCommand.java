import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventCommand implements Command {
    private final Task t;

    EventCommand(String arg) throws JasperException {
        String[] parts = arg.split("\\s+/(from|to)\\s+", 3);
        if (parts.length != 3) {
            throw new JasperException("Usage: event <task> /from <datetime> /to <datetime>");
        }
        String pattern = "yyyy-MM-dd HH:mm";
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern);
        try {
            t = new Event(parts[0], LocalDateTime.parse(parts[1], fmt), LocalDateTime.parse(parts[2], fmt));
        } catch (DateTimeParseException e) {
            throw new JasperException("Follow these datetime format(s): " + pattern);
        }
    }

    @Override
    public String execute(TaskList tasks) {
        tasks.add(t);
        return "Aye, aye. I've added this task:\n  " + t;
    }
}
