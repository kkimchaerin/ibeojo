package com.goming.weather.model;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDate;

public class LocalDateAdapter extends TypeAdapter<LocalDate> {

    @Override
    public void write(JsonWriter out, LocalDate value) throws IOException {
        out.beginObject();
        out.name("year").value(value.getYear());
        out.name("month").value(value.getMonthValue());
        out.name("day").value(value.getDayOfMonth());
        out.endObject();
    }

    @Override
    public LocalDate read(JsonReader in) throws IOException {
        in.beginObject();
        int year = 0, month = 0, day = 0;
        while (in.hasNext()) {
            String name = in.nextName();
            switch (name) {
                case "year":
                    year = in.nextInt();
                    break;
                case "month":
                    month = in.nextInt();
                    break;
                case "day":
                    day = in.nextInt();
                    break;
            }
        }
        in.endObject();
        return LocalDate.of(year, month, day);
    }
}
