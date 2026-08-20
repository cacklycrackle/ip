import java.util.List;

public interface Command {
    public String execute(List<Task> tasks) throws JasperException;

    public default boolean isQuit() {
        return false;
    }
}