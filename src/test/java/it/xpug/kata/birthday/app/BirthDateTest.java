package it.xpug.kata.birthday.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import it.xpug.kata.birthday.domain.BirthDate;
import org.junit.jupiter.api.Test;


public class BirthDateTest {

    @Test
    public void getters() {
        BirthDate date = BirthDate.of("1789/01/24");
        assertEquals(1, date.getMonth());
        assertEquals(24, date.getDay());
    }

    @Test
    public void isSameDate() {
        BirthDate date = BirthDate.of("1789/01/24");
        BirthDate sameDay = BirthDate.of("2001/01/24");
        BirthDate notSameDay = BirthDate.of("1789/01/25");
        BirthDate notSameMonth = BirthDate.of("1789/02/25");

        assertTrue(date.isSameDay(sameDay), "same");
        assertFalse(date.isSameDay(notSameDay), "not same day");
        assertFalse(date.isSameDay(notSameMonth), "not same month");
    }

    @Test
    public void equality() {
        BirthDate base = BirthDate.of("2000/01/02");
        BirthDate same = BirthDate.of("2000/01/02");
        BirthDate different = BirthDate.of("2000/01/04");

        assertNotEquals(null, base);
        assertNotEquals("", base);
        assertEquals(base, base);
        assertEquals(base, same);
        assertNotEquals(base, different);
    }
}
