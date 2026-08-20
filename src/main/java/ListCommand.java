import java.util.List;

public class ListCommand implements Command {
    ListCommand(String arg) throws JasperException {
        if (!arg.isEmpty()) {
            throw new JasperException("Usage: list");
        }
    }

    @Override
    public String execute(List<Task> tasks) {
        if (tasks.isEmpty()) {
            return "No tasks here! Add some to track.";
        }
        StringBuilder sb = new StringBuilder("Here are your tasks:\n");
        for (int i = 0; i < tasks.size(); ++i) {
            sb.append(String.format("%d.%s\n", i + 1, tasks.get(i)));
        }
        return sb.toString();
    }
}
