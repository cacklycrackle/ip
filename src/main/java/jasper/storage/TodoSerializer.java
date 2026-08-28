package jasper.storage;

import jasper.JasperException;
import jasper.task.Todo;

public class TodoSerializer {
    public static String serialize(Todo todo) {
        int status = todo.isDone() ? 1 : 0;
        return "T | " + status + " | " + todo.getDescription();
    }

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
