package it.xpug.kata.birthday.app;

import it.xpug.kata.birthday.domain.BirthdayUseCase;
import it.xpug.kata.birthday.infrastructure.CsvEmployeeRepo;
import it.xpug.kata.birthday.infrastructure.JavaxEmailService;
import java.time.Clock;

/**
 * The entry point of the application that triggers the birthday greetings logic.
 * <p>
 * This is where composition and creation of the objects is done and the computation starts.
 * <p>
 * Errors should be trapped here.
 */
public class Main implements Runnable {

    /**
     * Main method entry point of the program
     *
     * @param args program arguments
     */
    public static void main(String... args) {
        new Main().run();
    }

    /**
     * Starting method
     */
    @Override
    public void run() {
        try {
            
            var birthdayUseCase = new BirthdayUseCase(
                new CsvEmployeeRepo("employee_data.txt"),
                new JavaxEmailService("localhost", 25),
                Clock.systemDefaultZone());

            birthdayUseCase.sendGreetings();

        } catch (Exception e) {
            System.err.println("There was an error while sending the greetings");
            e.printStackTrace();
        }
    }
}
