import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineCommand implements Command {
    private final Task t;

    DeadlineCommand(String arg) throws JasperException {
        String[] parts = arg.split("\\s+/by\\s+", 2);
        if (parts.length != 2) {
            throw new JasperException("Usage: deadline <task> /by <datetime>");
        }
        String pattern = "yyyy-MM-dd HH:mm";
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern(pattern);
        try {
            t = new Deadline(parts[0], LocalDateTime.parse(parts[1].trim(), fmt));
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
