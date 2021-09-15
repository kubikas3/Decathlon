package main.com.ovi.Readers.Event;

import main.com.ovi.Models.Events.Event;
import main.com.ovi.Models.Exceptions.ReaderException;

import java.io.IOException;
import java.util.List;

public interface EventReader {
    Event read() throws ReaderException;
    List<Event> readAll() throws ReaderException;
    void close() throws ReaderException;
}
