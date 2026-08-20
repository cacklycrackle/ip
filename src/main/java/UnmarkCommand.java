import java.util.List;

public class UnmarkCommand implements Command {
    private final int n;

    UnmarkCommand(String arg) throws JasperException {
        try {
            this.n = Integer.parseInt(arg) - 1;
        } catch (NumberFormatException e) {
            throw new JasperException("Usage: unmark N (integer task index)");
        }
    }

    @Override
    public String execute(List<Task> tasks) throws JasperException {
        if (n < 0 || n >= tasks.size()) {
            throw new JasperException("Task index out of range");
        }
        tasks.get(n).markAsUndone();
        return "Get to work... I've marked this task as not done yet\n  " + tasks.get(n);

    }
}
