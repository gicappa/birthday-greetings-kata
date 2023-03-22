package it.xpug.kata.birthday.domain;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * The representation of a birthday in the domain
 */
public record BirthDate(Date date) {

    public BirthDate() {
        this(new Date());
    }

    public static BirthDate of(String date) {
        try {
            return new BirthDate(new SimpleDateFormat("yyyy/MM/dd").parse(date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public int getDay() {
        return getPartOfDate(DAY_OF_MONTH);
    }

    public int getMonth() {
        return 1 + getPartOfDate(MONTH);
    }

    public boolean isSameDay(BirthDate anotherDate) {
        return anotherDate.getDay() == this.getDay() && anotherDate.getMonth() == this.getMonth();
    }

    private int getPartOfDate(int part) {
        var calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(part);
    }
}
