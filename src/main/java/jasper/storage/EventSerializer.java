package jasper.storage;

import jasper.JasperException;
import jasper.task.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class EventSerializer {
    public static String serialize(Event e) {
        int status = e.getDone() ? 1 : 0;
        return "E | " + status + " | " + e.getDescription() + " | " + e.getFrom() + " | " + e.getTo();
    }

    public static Event deserialize(String line) throws JasperException {
        String[] parts = line.split(" \\| ", 5);
        if (parts.length < 5) {
            throw new JasperException("Error reading or loading savefile!");
        }
        Event event;
        try {
            event = new Event(parts[2], LocalDateTime.parse(parts[3]), LocalDateTime.parse(parts[4]));
        } catch (DateTimeParseException e) {
            throw new JasperException("Error reading or loading savefile!");
        }
        if (parts[1].equals("1")) {
            event.markDone();
        }
        return event;
    }
}
