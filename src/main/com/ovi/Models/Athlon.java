package main.com.ovi.Models;

import main.com.ovi.Models.Events.Event;
import main.com.ovi.Models.Exceptions.DecathlonException;

import java.util.List;


public class Athlon {
    protected final List<Event> events;

    public Athlon(List<Event> events) {
        this.events = events;
    }

    public List<Event> getEvents() {
        return events;
    }

    public int getMark(List<Double> performance) throws DecathlonException {
        int total = 0;

        for (int i = 0; i < events.size(); i++) {
            total += events.get(i).getPoints(performance.get(i));
        }

        return total;
    }
}
