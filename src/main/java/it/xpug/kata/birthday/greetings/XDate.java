package it.xpug.kata.birthday.greetings;

import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * A date object that is mostly a wrapper to date to parse
 * and represent a birthday in the domain.
 * <br/>
 * This is a good candidate to be a record and to have birthday
 * as name.
 */
public class XDate {

    private final Date date;

    public XDate() {
        date = new Date();
    }

    public XDate(String yyyyMMdd) throws ParseException {
        date = new SimpleDateFormat("yyyy/MM/dd").parse(yyyyMMdd);
    }

    public int getDay() {
        return getPartOfDate(DAY_OF_MONTH);
    }

    public int getMonth() {
        return 1 + getPartOfDate(MONTH);
    }

    public boolean isSameDay(XDate anotherDate) {
        return anotherDate.getDay() == this.getDay() && anotherDate.getMonth() == this.getMonth();
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof XDate other)) {
            return false;
        }
        return other.date.equals(this.date);
    }

    private int getPartOfDate(int part) {
        var calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar.get(part);
    }
}
