package it.xpug.kata.birthday.greetings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class EmployeeTest {

    @Test
    public void testBirthday() throws Exception {
        Employee employee = new Employee("foo", "bar", "1990/01/31", "a@b.c");
        assertFalse(employee.hasBirthday(new BirthDate("2008/01/30")), "not his birthday");
        assertTrue(employee.hasBirthday(new BirthDate("2008/01/31")), "his birthday");
    }

    @Test
    public void equality() throws Exception {
        Employee base = new Employee("First", "Last", "1999/09/01", "first@last.com");
        Employee same = new Employee("First", "Last", "1999/09/01", "first@last.com");
        Employee different = new Employee("First", "Last", "1999/09/01", "boom@boom.com");

        assertNotEquals(null, base);
        assertNotEquals("", base);
        assertEquals(base, same);
        assertNotEquals(base, different);
    }
}
