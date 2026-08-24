import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Storage {
    private static final Pattern BASE_PATTERN = Pattern.compile("[TDE] \\| [01] \\| (.+)$");
    private final Path path;

    Storage(String parent, String filename) {
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

                if (!BASE_PATTERN.matcher(line).matches()) {
                    throw new JasperException("Error reading or loading savefile!");
                }
                Task t;
                String[] parts;
                switch (line.charAt(0)) {
                    case 'T':
                        parts = line.split(" \\| ", 3);
                        t = new Todo(parts[2]);
                        break;
                    case 'D':
                        parts = line.split(" \\| ", 4);
                        if (parts.length < 4) throw new JasperException("Error reading or loading savefile!");
                        t = new Deadline(parts[2], parts[3]);
                        break;
                    case 'E':
                        parts = line.split(" \\| ", 5);
                        if (parts.length < 5) throw new JasperException("Error reading or loading savefile!");
                        t = new Event(parts[2], parts[3], parts[4]);
                        break;
                    default:
                        throw new IllegalStateException();
                }
                if (parts[1].equals("1")) {
                    t.markAsDone();
                }
                tasks.add(t);
            }
            reader.close();
            return tasks;
        } catch (IOException e) {
            throw new JasperException("Error reading or loading savefile!");
        }
    }

    public void save(List<Task> tasks) throws JasperException {
        try {
            Files.createDirectories(path.getParent());
            List<String> lines = new ArrayList<>();

            for (Task task : tasks) {
                int status = task.isDone ? 1 : 0;
                String entry = switch (task) {
                    case Todo t -> String.format("T | %d | %s", status, t.description);
                    case Deadline d -> String.format("D | %d | %s | %s", status, d.description, d.by);
                    case Event e -> String.format("E | %d | %s | %s | %s", status, e.description, e.from, e.to);
                    default -> throw new IllegalStateException("Missing implementation!");
                };
                lines.add(entry);
            }
            Files.write(path, lines);
        } catch (IOException e) {
            throw new JasperException("Could not save tasks: " + e.getMessage());
        }
    }
}
