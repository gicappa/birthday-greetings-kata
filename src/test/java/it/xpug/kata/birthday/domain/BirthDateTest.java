package it.xpug.kata.birthday.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class BirthDateTest {

    @Test
    public void isSameDate() {
        BirthDate date = BirthDate.of("1789/01/24");
        LocalDate sameDay = LocalDate.of(2001, 1, 24);
        LocalDate notSameDay = LocalDate.of(1789, 1, 25);
        LocalDate notSameMonth = LocalDate.of(1789, 2, 24);

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
        assertEquals(base, base);
        assertEquals(base, same);
        assertNotEquals(base, different);
    }
}
