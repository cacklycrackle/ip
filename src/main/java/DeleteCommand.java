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
    public String execute(TaskList tasks) throws JasperException {
        Task t = tasks.delete(n);
        return "This task shall be terminated, if you insist:\n  " + t
                + "\n1 task down, " + tasks.size() + " to go.";

    }
}
