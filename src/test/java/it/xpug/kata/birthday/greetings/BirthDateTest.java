package it.xpug.kata.birthday.greetings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;


public class BirthDateTest {

    @Test
    public void getters() throws Exception {
        BirthDate date = new BirthDate("1789/01/24");
        assertEquals(1, date.getMonth());
        assertEquals(24, date.getDay());
    }

    @Test
    public void isSameDate() throws Exception {
        BirthDate date = new BirthDate("1789/01/24");
        BirthDate sameDay = new BirthDate("2001/01/24");
        BirthDate notSameDay = new BirthDate("1789/01/25");
        BirthDate notSameMonth = new BirthDate("1789/02/25");

        assertTrue(date.isSameDay(sameDay), "same");
        assertFalse(date.isSameDay(notSameDay), "not same day");
        assertFalse(date.isSameDay(notSameMonth), "not same month");
    }

    @Test
    public void equality() throws Exception {
        BirthDate base = new BirthDate("2000/01/02");
        BirthDate same = new BirthDate("2000/01/02");
        BirthDate different = new BirthDate("2000/01/04");

        assertNotEquals(null, base);
        assertNotEquals("", base);
        assertEquals(base, base);
        assertEquals(base, same);
        assertNotEquals(base, different);
    }

}
