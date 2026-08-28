package jasper.storage;

import jasper.JasperException;
import jasper.task.Todo;

/**
 * Provides methods to serialize and deserialize Todo tasks.
 */
public class TodoSerializer {
    /**
     * Serializes a Todo task into a formatted string.
     *
     * @param todo Todo task to serialize.
     * @return Formatted string representation of the task.
     */
    public static String serialize(Todo todo) {
        int status = todo.isDone() ? 1 : 0;
        return "T | " + status + " | " + todo.getDescription();
    }

    /**
     * Deserializes a formatted string into a Todo task.
     *
     * @param line String representation of the task to be parsed.
     * @return Deserialized Todo task.
     * @throws JasperException If the string format is invalid.
     */
    public static Todo deserialize(String line) throws JasperException {
        String[] parts = line.split(" \\| ", 3);
        if (parts.length < 3) {
            throw new JasperException("Error reading or loading savefile!");
        }
        Todo todo = new Todo(parts[2]);
        if (parts[1].equals("1")) {
            todo.markDone();
        }
        return todo;
    }
}
