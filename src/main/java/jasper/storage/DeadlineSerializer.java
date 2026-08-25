package jasper.storage;

import jasper.JasperException;
import jasper.task.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DeadlineSerializer {
    public static String serialize(Deadline d) {
        int status = d.getDone() ? 1 : 0;
        return "D | " + status + " | " + d.getDescription() + " | " + d.getBy();
    }

    public static Deadline deserialize(String line) throws JasperException {
        String[] parts = line.split(" \\| ", 4);
        if (parts.length < 4) {
            throw new JasperException("Error reading or loading savefile!");
        }
        Deadline deadline;
        try {
            deadline = new Deadline(parts[2], LocalDateTime.parse(parts[3]));
        } catch (DateTimeParseException e) {
            throw new JasperException("Error reading or loading savefile!");
        }
        if (parts[1].equals("1")) {
            deadline.markDone();
        }
        return deadline;
    }
}
