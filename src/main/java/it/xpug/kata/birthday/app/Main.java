package it.xpug.kata.birthday.app;

import it.xpug.kata.birthday.domain.BirthdayUseCase;
import it.xpug.kata.birthday.infrastructure.CsvEmployeeRepository;
import it.xpug.kata.birthday.infrastructure.JavaxEmailService;
import java.io.IOException;
import java.time.Clock;

public class Main {

    /**
     * The entry point of the application that triggers the birthday greetings logic <br/> This is
     * the class where the object should be instantiated and start to be brought to life. <br/> This
     * is also where error should be reported.
     *
     * @param args application arguments
     * @throws IOException        error while reading the file.
     */
    public static void main(String... args) throws IOException {

        var birthdayGreetingsUseCase = new BirthdayUseCase(
            new CsvEmployeeRepository("employee_data.txt"),
            new JavaxEmailService("localhost", 25),
            Clock.systemDefaultZone());

        birthdayGreetingsUseCase.sendGreetings();
    }

}
