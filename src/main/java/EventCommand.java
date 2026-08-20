import java.util.List;

public class EventCommand implements Command {
    private final String[] parts;

    EventCommand(String arg) throws JasperException {
        String[] parts = arg.split("\\s+/(from|to)\\s+", 3);
        if (parts.length != 3) {
            throw new JasperException("Usage: event <task> /from <datetime> /to <datetime>");
        }
        this.parts = parts;
    }

    @Override
    public String execute(List<Task> tasks) {
        Task t = new Event(parts[0], parts[1], parts[2]);
        tasks.add(t);
        return "Aye, aye. I've added this task:\n  " + t;
    }
}
