package main.com.ovi.Models;

import main.com.ovi.Models.Events.Event;
import main.com.ovi.Models.Exceptions.DecathlonException;

import java.util.List;

public class Decathlon extends Athlon {
    public Decathlon(List<Event> events) throws DecathlonException {
        super(events);

        if (events.size() != 10) {
            throw new DecathlonException("Event count must be equal to 10.");
        }
    }

    @Override
    public int getMark(List<Double> performance) throws DecathlonException {
        if (performance.size() != 10) {
            throw new DecathlonException("Performance result count must be equal to 10.");
        }

        return super.getMark(performance);
    }
}
