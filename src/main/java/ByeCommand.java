public class ByeCommand implements Command {
    ByeCommand(String arg) throws JasperException {
        if (!arg.isEmpty()) {
            throw new JasperException("Usage: bye");
        }
    }

    @Override
    public String execute(TaskList tasks) {
        return "Farewell. Hope to see you again soon!";
    }

    @Override
    public boolean isQuit() {
        return true;
    }
}
