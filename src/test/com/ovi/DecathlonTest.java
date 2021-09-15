package test.com.ovi;

import main.com.ovi.Models.Decathlon;
import main.com.ovi.Models.Events.*;
import main.com.ovi.Models.Exceptions.DecathlonException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DecathlonTest {
    static final List<Event> events = List.of(
            new TrackEvent("100 m", 25.4347, 18, 1.81),
            new JumpEvent("Long jump", 0.14354, 220, 1.4),
            new FieldEvent("Shot put", 51.39, 1.5, 1.05),
            new JumpEvent("High jump", 0.8465, 75, 1.42),
            new TrackEvent("400 m", 1.53775, 82, 1.81),
            new TrackEvent("110 m hurdles", 5.74352, 28.5, 1.92),
            new FieldEvent("Discus throw", 12.91, 4, 1.1),
            new JumpEvent("Pole vault", 0.2797, 100, 1.35),
            new FieldEvent("Javelin throw", 10.14, 7, 1.08),
            new RaceEvent("1500 m", 0.03768, 480, 1)
    );

    static final List<Double> performance = List.of(
            10.55,
            7.80,
            16.00,
            2.05,
            48.42,
            13.75,
            50.54,
            5.45,
            71.90,
            276.11
    );

    @Test
    void ThrowsExceptionWhenEventCountNotEqualTen() {
        DecathlonException ex = Assertions.assertThrows(DecathlonException.class, () -> {
            new Decathlon(events.subList(0, 1));
        });

        String expectedMessage = "Event count must be equal to 10.";
        String actualMessage = ex.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    void ThrowsExceptionWhenPerformanceCountNotEqualTen() {
        DecathlonException ex = Assertions.assertThrows(DecathlonException.class, () -> {

        });

        String expectedMessage = "Event count must be equal to 10.";
        String actualMessage = ex.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
