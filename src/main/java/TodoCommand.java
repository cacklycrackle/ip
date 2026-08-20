import java.util.List;

public class TodoCommand implements Command {
    private final String arg;

    TodoCommand(String arg) throws JasperException {
        if (arg.isEmpty()) {
            throw new JasperException("Usage: todo <task>");
        }
        this.arg = arg;
    }

    @Override
    public String execute(List<Task> tasks) {
        Task t = new Todo(arg);
        tasks.add(t);
        return "Aye, aye. I've added this task:\n  " + t;
    }
}
