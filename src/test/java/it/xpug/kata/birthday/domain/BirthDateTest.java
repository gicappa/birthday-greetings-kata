package it.xpug.kata.birthday.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;

class BirthDateTest {

    @Test
    void isSameDate() {
        var date = BirthDate.of("1789/01/24");
        var sameDay = LocalDate.of(2001, 1, 24);
        var notSameDay = LocalDate.of(1789, 1, 25);
        var notSameMonth = LocalDate.of(1789, 2, 24);

        assertTrue(date.isSameDayAndMonth(sameDay), "same");
        assertFalse(date.isSameDayAndMonth(notSameDay), "not same day");
        assertFalse(date.isSameDayAndMonth(notSameMonth), "not same month");
    }

    @Test
    void equality() {
        var base = BirthDate.of("2000/01/02");
        var same = BirthDate.of("2000/01/02");
        var different = BirthDate.of("2000/01/04");

        assertNotEquals(null, base);
        assertEquals(base, base);
        assertEquals(base, same);
        assertNotEquals(base, different);
    }
}
