package main.com.ovi.Models.Events;

import main.com.ovi.Constants;

public class JumpEvent extends FieldEvent {
    public JumpEvent(String name, double a, double b, double c) {
        super(name, a, b, c);
    }

    @Override
    public int getPoints(double p) {
        return super.getPoints(p * Constants.METER_TO_CM);
    }
}
