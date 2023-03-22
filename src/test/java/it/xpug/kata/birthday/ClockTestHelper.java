package it.xpug.kata.birthday;

import static java.time.ZoneOffset.UTC;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class ClockTestHelper {
    public static Clock clockAt(int year, int month, int day) {
        return Clock.fixed(dateAt(year, month, day), ZoneId.of("CET"));
    }

    public static Instant dateAt(int year, int month, int day) {
        return LocalDate.of(year, month, day)
            .atTime(0, 0)
            .toInstant(UTC);
    }
}
