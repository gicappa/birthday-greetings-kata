package it.xpug.kata.birthday.domain;

/**
 * The employee representation This is a value object that holds the employee information It could
 * be refactored as a Record
 */
public record Employee(String firstName, String lastName, BirthDate birthDate, String email) {

    public Employee(String firstName, String lastName, String birthDate, String email) {
        this(firstName, lastName, BirthDate.of(birthDate), email);
    }

    public boolean hasBirthday(BirthDate today) {
        return today.isSameDay(birthDate);
    }

}
