public interface Command {
    public String execute(TaskList tasks) throws JasperException;

    public default boolean isQuit() {
        return false;
    }
}