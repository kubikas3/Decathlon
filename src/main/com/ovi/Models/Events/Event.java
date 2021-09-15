package main.com.ovi.Models.Events;

public abstract class Event {
    protected String name;
    protected double a, b, c;

    public Event(String name, double a, double b, double c) {
        this.name = name;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public String getName() {
        return name;
    }

    public abstract int getPoints(double p);
}
