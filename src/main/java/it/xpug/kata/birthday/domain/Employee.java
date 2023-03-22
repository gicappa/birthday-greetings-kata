package it.xpug.kata.birthday.domain;

import it.xpug.kata.birthday.greetings.BirthDate;
import java.text.ParseException;

/**
 * The employee representation This is a value object that holds the employee information It could
 * be refactored as a Record
 */
public record Employee(String firstName, String lastName, BirthDate birthDate, String email) {

    public Employee(String firstName, String lastName, String birthDate, String email)
        throws ParseException {
        this(firstName, lastName, new BirthDate(birthDate), email);
    }

    public boolean hasBirthday(BirthDate today) {
        return today.isSameDay(birthDate);
    }

}
