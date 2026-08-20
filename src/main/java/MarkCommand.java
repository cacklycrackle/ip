import java.util.List;

public class MarkCommand implements Command {
    private final int n;

    MarkCommand(String arg) throws JasperException {
        try {
            this.n = Integer.parseInt(arg) - 1;
        } catch (NumberFormatException e) {
            throw new JasperException("Usage: mark N (integer task index)");
        }
    }

    @Override
    public String execute(List<Task> tasks) throws JasperException {
        if (n < 0 || n >= tasks.size()) {
            throw new JasperException("Task index out of range");
        }
        tasks.get(n).markAsDone();
        return "Alright! I've marked this task as done\n  " + tasks.get(n);

    }
}
