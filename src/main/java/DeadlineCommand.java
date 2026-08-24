public class DeadlineCommand implements Command {
    private final Task t;

    DeadlineCommand(String arg) throws JasperException {
        String[] parts = arg.split("\\s+/by\\s+", 2);
        if (parts.length != 2) {
            throw new JasperException("Usage: deadline <task> /by <datetime>");
        }
        t = new Deadline(parts[0], Parser.parseDateTime(parts[1]));
    }

    @Override
    public String execute(TaskList tasks) {
        tasks.add(t);
        return "Aye, aye. I've added this task:\n  " + t;
    }
}
