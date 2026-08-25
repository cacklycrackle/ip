package jasper.storage;

import jasper.JasperException;
import jasper.task.Deadline;
import jasper.task.Event;
import jasper.task.Task;
import jasper.task.TaskList;
import jasper.task.Todo;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private final Path path;

    public Storage(String parent, String filename) {
        path = Paths.get(parent, filename);
    }

    public List<Task> load() throws JasperException {
        List<Task> tasks = new ArrayList<>(100);
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
                return tasks;
            }
            BufferedReader reader = Files.newBufferedReader(path);
            while (true) {
                String line = reader.readLine();
                if (line == null) {
                    break;
                }
                Task t = switch (line.charAt(0)) {
                    case 'T' -> TodoSerializer.deserialize(line);
                    case 'D' -> DeadlineSerializer.deserialize(line);
                    case 'E' -> EventSerializer.deserialize(line);
                    default -> throw new JasperException("Error reading or loading savefile!");
                };
                tasks.add(t);
            }
            reader.close();
            return tasks;
        } catch (IOException | DateTimeParseException e) {
            throw new JasperException("Error reading or loading savefile!");
        }
    }

    public void save(TaskList tasks) throws JasperException {
        try {
            Files.createDirectories(path.getParent());
            List<String> lines = new ArrayList<>();
            for (Task task : tasks) {
                String entry = switch (task) {
                    case Todo t -> TodoSerializer.serialize(t);
                    case Deadline d -> DeadlineSerializer.serialize(d);
                    case Event e -> EventSerializer.serialize(e);
                    default -> throw new IllegalStateException("Unsupported task type!");
                };
                lines.add(entry);
            }
            Files.write(path, lines);
        } catch (IOException e) {
            throw new JasperException("Could not save tasks: " + e.getMessage());
        }
    }
}
