package it.xpug.kata.birthday.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The representation of a birthday in the domain
 */
public record BirthDate(LocalDate date) {

    public static BirthDate of(String date) {
        return new BirthDate(LocalDate.parse(
            date, DateTimeFormatter.ofPattern("yyyy/MM/dd")));
    }

    public boolean isSameDay(LocalDate today) {
        return date.getDayOfMonth() == today.getDayOfMonth() &&
            date.getMonthValue() == today.getMonthValue();
    }
}
