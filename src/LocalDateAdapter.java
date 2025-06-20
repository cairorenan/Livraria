import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDate;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {

    @Override
    public void write(JsonWriter writer, LocalDate date) throws IOException {
        if (date == null) {
            writer.nullValue();
        } else {
            writer.value(date.toString());
        }
    }

    @Override
    public LocalDate read(JsonReader reader) throws IOException {
        String dateStr = reader.nextString();
        return LocalDate.parse(dateStr);
    }
}
