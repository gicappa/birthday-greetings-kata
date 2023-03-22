package it.xpug.kata.birthday.greetings;

import java.io.IOException;
import java.text.ParseException;
import javax.mail.MessagingException;

public class Main {

    /**
     * The entry point of the application that triggers the birthday
     * greetings logic
     * <br/>
     * This is the class where the object should be instantiated
     * and start to be brought to life.
     * <br/>
     * This is also where error should be reported.
     *
     * @param args application arguments
     * @throws IOException error while reading the file.
     * @throws ParseException error while parsing the date.
     * @throws MessagingException error while sending the email.
     */
    public static void main(String... args)
        throws IOException, ParseException, MessagingException {

        var service = new BirthdayService();

        service.sendGreetings(
            "employee_data.txt",
            new BirthDate(),
            "localhost",
            25);
    }

}
