package it.xpug.kata.birthday.domain;

import java.time.LocalDate;

/**
 * This record contains the employee PII with the birthday
 */
public record Employee(String firstName, String lastName, BirthDate birthDate, String email) {

    public Employee(String firstName, String lastName, String birthDate, String email) {
        this(firstName, lastName, BirthDate.of(birthDate), email);
    }

    public boolean hasBirthday(LocalDate date) {
        return birthDate.isSameDayAndMonth(date);
    }
}
