package it.xpug.kata.birthday.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Wrapping the LocalDate in a birthdate to better handle equality and birthday checks
 */
public record BirthDate(LocalDate date) {

    public static BirthDate of(String date) {
        return new BirthDate(parseYyyyMMdd(date));
    }

    private static LocalDate parseYyyyMMdd(String date) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
    }

    public boolean isSameDayAndMonth(LocalDate today) {
        return isSameDay(today) && isSameMonth(today);
    }

    private boolean isSameMonth(LocalDate today) {
        return date.getMonthValue() == today.getMonthValue();
    }

    private boolean isSameDay(LocalDate today) {
        return date.getDayOfMonth() == today.getDayOfMonth();
    }
}
