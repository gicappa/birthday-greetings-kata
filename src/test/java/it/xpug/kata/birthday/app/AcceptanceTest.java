package it.xpug.kata.birthday.app;

import static it.xpug.kata.birthday.ClockTestHelper.clockAt;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;
import it.xpug.kata.birthday.domain.BirthdayUseCase;
import it.xpug.kata.birthday.infrastructure.CsvEmployeeRepository;
import it.xpug.kata.birthday.infrastructure.JavaxEmailService;
import java.io.FileNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("BirthdayGreetingsUseCase")
public class AcceptanceTest {

    private static final int NONSTANDARD_PORT = 9999;
    private BirthdayUseCase birthdayUseCase;
    private SimpleSmtpServer mailServer;

    @BeforeEach
    public void before() {
        mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT);
    }

    @Nested
    @DisplayName("when it's somebody's birthday")
    class Birthday {

        private SmtpMessage message;

        @BeforeEach
        void before() throws FileNotFoundException {
            birthdayUseCase =
                new BirthdayUseCase(
                    new CsvEmployeeRepository("employee_data.txt"),
                    new JavaxEmailService("localhost", NONSTANDARD_PORT),
                    clockAt(2008, 10, 8));

            birthdayUseCase.sendGreetings();
            message = (SmtpMessage) mailServer.getReceivedEmail().next();
        }

        @Test
        public void it_sends_greetings() {
            assertEquals(1, mailServer.getReceivedEmailSize(), "message not sent?");
        }

        @Test
        public void it_has_a_subject() {
            assertEquals("Happy Birthday!", message.getHeaderValue("Subject"));
        }

        @Test
        public void it_has_a_body() {
            assertEquals("Happy Birthday, dear John!", message.getBody());
        }

        @Test
        public void it_has_a_recipient() {
            var recipients = message.getHeaderValues("To");
            assertEquals(1, recipients.length);
            assertEquals("john.doe@foobar.com", recipients[0]);
        }

    }


    @Nested
    @DisplayName("when it's somebody's birthday")
    class NotBirthday {

        @BeforeEach
        void before() throws FileNotFoundException {
            birthdayUseCase =
                new BirthdayUseCase(
                    new CsvEmployeeRepository("employee_data.txt"),
                    new JavaxEmailService("localhost", NONSTANDARD_PORT),
                    clockAt(2008, 1, 1));

            birthdayUseCase.sendGreetings();
        }

        @Test
        public void it_does_not_send_email() {
            assertEquals(0, mailServer.getReceivedEmailSize(), "what? messages?");
        }
    }


    @AfterEach
    public void after() throws Exception {
        mailServer.stop();

        Thread.sleep(200);
    }

}
