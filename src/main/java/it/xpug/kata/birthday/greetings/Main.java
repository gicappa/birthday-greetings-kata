package it.xpug.kata.birthday.greetings;

import java.io.IOException;
import java.text.ParseException;
import javax.mail.MessagingException;

public class Main {

    public static void main(String... args)
        throws IOException, ParseException, MessagingException {

        var service = new BirthdayService();

        service.sendGreetings(
            "employee_data.txt",
            new XDate(),
            "localhost",
            25);
    }

}
