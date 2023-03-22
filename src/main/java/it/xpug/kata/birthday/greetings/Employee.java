package it.xpug.kata.birthday.greetings;

import java.text.ParseException;

/**
 * The employee representation
 * This is a value object that holds the employee information
 * It could be refactored as a Record
 */
public class Employee {

    private final BirthDate birthDate;
    private final String lastName;
    private final String firstName;
    private final String email;

    public Employee(String firstName, String lastName, String birthDate, String email)
        throws ParseException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = new BirthDate(birthDate);
        this.email = email;
    }

    public boolean isBirthday(BirthDate today) {
        return today.isSameDay(birthDate);
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {
        return "Employee " + firstName + " " + lastName + " <" + email + "> born " + birthDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + birthDate.hashCode();
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Employee other)) {
            return false;
        }
        if (!birthDate.equals(other.birthDate)) {
            return false;
        }
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }
        if (firstName == null) {
            if (other.firstName != null) {
                return false;
            }
        } else if (!firstName.equals(other.firstName)) {
            return false;
        }
        if (lastName == null) {
            return other.lastName == null;
        } else {
            return lastName.equals(other.lastName);
        }
    }


}
