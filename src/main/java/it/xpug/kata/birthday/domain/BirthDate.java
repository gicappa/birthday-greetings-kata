package it.xpug.kata.birthday.domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * The representation of a birthday in the domain
 */
public record BirthDate(LocalDate date) {

    public BirthDate() {
        this(LocalDate.now());
    }

    public static BirthDate of(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return new BirthDate(LocalDate.parse(date, formatter));
    }

    public int getDay() {
        return date.getDayOfMonth();
    }

    public int getMonth() {
        return date.getMonthValue();
    }

    public boolean isSameDay(BirthDate anotherDate) {
        return anotherDate.getDay() == this.getDay() && anotherDate.getMonth() == this.getMonth();
    }

}
