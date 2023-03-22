package it.xpug.kata.birthday.app;

import it.xpug.kata.birthday.domain.BirthDate;
import it.xpug.kata.birthday.domain.BirthdayGreetingsUseCase;
import it.xpug.kata.birthday.infrastructure.CsvEmployeeRepository;
import it.xpug.kata.birthday.infrastructure.JavaxEmailService;
import java.io.IOException;
import java.text.ParseException;
import javax.mail.MessagingException;

public class Main {

    /**
     * The entry point of the application that triggers the birthday greetings logic <br/> This is
     * the class where the object should be instantiated and start to be brought to life. <br/> This
     * is also where error should be reported.
     *
     * @param args application arguments
     * @throws IOException        error while reading the file.
     * @throws ParseException     error while parsing the date.
     * @throws MessagingException error while sending the email.
     */
    public static void main(String... args)
        throws IOException, ParseException, MessagingException {

        var service = new BirthdayGreetingsUseCase(
            new CsvEmployeeRepository("employee_data.txt"),
            new JavaxEmailService("localhost", 25)
        );

        service.sendGreetings(
            new BirthDate(),
            "localhost",
            25);
    }

}
