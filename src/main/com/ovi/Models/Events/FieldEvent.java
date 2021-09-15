package main.com.ovi.Models.Events;

public class FieldEvent extends Event {
    public FieldEvent(String name, double a, double b, double c) {
        super(name, a, b, c);
    }

    @Override
    public int getPoints(double p) {
        return (int)(a * Math.pow(p - b, c));
    }
}
