package main.com.ovi.Readers.Event;

import main.com.ovi.Constants;
import main.com.ovi.Models.Events.*;
import main.com.ovi.Models.Exceptions.ReaderException;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVEventReader implements EventReader {
    private final String filename;
    private final String delimiter;
    private final BufferedReader reader;

    public CSVEventReader(String fileName, String delimiter) throws ReaderException {
        this.filename = fileName;
        this.delimiter = delimiter;
        try {
            this.reader = new BufferedReader(new FileReader(fileName));
        } catch (FileNotFoundException e) {
            throw new ReaderException("Failed to read an event input file.", e);
        }
    }

    @Override
    public Event read() throws ReaderException {
        try {
            String line = reader.readLine();

            if (line != null) {
                String[] values = line.trim().split("\\s*" + delimiter + "\\s*");
                String eventType = validateAndParseEventType(values[0]);
                String eventName = values[1];
                double a = validateAndParseValue(values[2]);
                double b = validateAndParseValue(values[3]);
                double c = validateAndParseValue(values[4]);

                switch (eventType) {
                    case Constants.TRACK_EVENT:
                        return new TrackEvent(eventName, a, b, c);
                    case Constants.FIELD_EVENT:
                        return new FieldEvent(eventName, a, b, c);
                    case Constants.JUMP_EVENT:
                        return new JumpEvent(eventName, a, b, c);
                    case Constants.RACE_EVENT:
                        return new RaceEvent(eventName, a, b, c);
                }
            }

            return null;
        } catch (IOException e) {
            throw new ReaderException("Failed to read an event from input file.", e);
        }
    }

    @Override
    public List<Event> readAll() throws ReaderException {
        List<Event> result = new ArrayList<>();
        Event event;

        while ((event = read()) != null) {
            result.add(event);
        }

        return result;
    }

    @Override
    public void close() throws ReaderException {
        try {
            reader.close();
        } catch (IOException e) {
            throw new ReaderException("Failed to close event input file.", e);
        }
    }

    private String validateAndParseEventType(String eventType) throws ReaderException {
        if (!eventType.equals(Constants.TRACK_EVENT) &&
                !eventType.equals(Constants.FIELD_EVENT) &&
                !eventType.equals(Constants.JUMP_EVENT) &&
                !eventType.equals(Constants.RACE_EVENT)) {
            throw new ReaderException("Event type is invalid.");
        }

        return eventType;
    }

    private double validateAndParseValue(String value) throws ReaderException {
        String regex = "\\d+(.\\d+)?";

        if (!value.matches(regex)) {
            throw new ReaderException("Event input file is invalid.");
        }

        return Double.parseDouble(value);
    }
}
