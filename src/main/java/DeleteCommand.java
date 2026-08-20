import java.util.List;

public class DeleteCommand implements Command {
    private final int n;

    DeleteCommand(String arg) throws JasperException {
        try {
            this.n = Integer.parseInt(arg) - 1;
        } catch (NumberFormatException e) {
            throw new JasperException("Usage: delete N (integer task index)");
        }
    }

    @Override
    public String execute(List<Task> tasks) throws JasperException {
        if (n < 0 || n >= tasks.size()) {
            throw new JasperException("Task index out of range");
        }
        Task t = tasks.remove(n);
        return "This task shall be terminated, if you insist:\n  " + t
                + "\n1 task down, " + tasks.size() + " to go.";

    }
}
