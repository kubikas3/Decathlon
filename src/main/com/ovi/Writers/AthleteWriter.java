package main.com.ovi.Writers;

import main.com.ovi.Models.Athlete;
import main.com.ovi.Models.Exceptions.WriterException;

import java.util.List;

public interface AthleteWriter {
    void write(List<Athlete> athletes) throws WriterException;
}
