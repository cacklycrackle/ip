import java.util.List;

public class DeadlineCommand implements Command {
    private final String[] parts;

    DeadlineCommand(String arg) throws JasperException {
        String[] parts = arg.split("\\s+/by\\s+", 2);
        if (parts.length != 2) {
            throw new JasperException("Usage: deadline <task> /by <datetime>");
        }
        this.parts = parts;
    }

    @Override
    public String execute(List<Task> tasks) {
        Task t = new Deadline(parts[0], parts[1]);
        tasks.add(t);
        return "Aye, aye. I've added this task:\n  " + t;
    }
}
