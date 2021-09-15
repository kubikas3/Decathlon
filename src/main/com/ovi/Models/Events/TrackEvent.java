package main.com.ovi.Models.Events;

public class TrackEvent extends Event {
    public TrackEvent(String name, double a, double b, double c) {
        super(name, a, b, c);
    }

    @Override
    public int getPoints(double p) {
        return (int)(a * Math.pow(b - p, c));
    }
}
