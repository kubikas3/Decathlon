package main.com.ovi.Readers.Athlete;

import main.com.ovi.Models.Athlete;
import main.com.ovi.Models.Exceptions.ReaderException;

import java.io.IOException;
import java.util.List;

public interface AthleteReader {
    Athlete read() throws ReaderException;
    List<Athlete> readAll() throws ReaderException;
    void close() throws ReaderException;
}
