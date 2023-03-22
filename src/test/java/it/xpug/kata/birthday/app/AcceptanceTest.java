package it.xpug.kata.birthday.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;
import it.xpug.kata.birthday.domain.BirthDate;
import it.xpug.kata.birthday.domain.BirthdayGreetingsUseCase;
import it.xpug.kata.birthday.infrastructure.CsvEmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class AcceptanceTest {

    private static final int NONSTANDARD_PORT = 9999;
    private BirthdayGreetingsUseCase birthdayGreetingsUseCase;
    private SimpleSmtpServer mailServer;

    @BeforeEach
    public void setUp() {
        mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT);
        birthdayGreetingsUseCase =
            new BirthdayGreetingsUseCase(
                new CsvEmployeeRepository("employee_data.txt"));
    }

    @AfterEach
    public void tearDown() throws Exception {
        mailServer.stop();
        Thread.sleep(200);
    }

    @Test
    public void willSendGreetings_whenItsSomebodysBirthday() throws Exception {

        birthdayGreetingsUseCase.sendGreetings(
            "employee_data.txt",
            new BirthDate("2008/10/08"),
            "localhost",
            NONSTANDARD_PORT);

        assertEquals(1, mailServer.getReceivedEmailSize(), "message not sent?");

        var message = (SmtpMessage) mailServer.getReceivedEmail().next();
        assertEquals("Happy Birthday, dear John!", message.getBody());
        assertEquals("Happy Birthday!", message.getHeaderValue("Subject"));

        var recipients = message.getHeaderValues("To");
        assertEquals(1, recipients.length);
        assertEquals("john.doe@foobar.com", recipients[0]);
    }

    @Test
    public void willNotSendEmailsWhenNobodysBirthday() throws Exception {
        birthdayGreetingsUseCase.sendGreetings(
            "employee_data.txt",
            new BirthDate("2008/01/01"),
            "localhost",
            NONSTANDARD_PORT);

        assertEquals(0, mailServer.getReceivedEmailSize(), "what? messages?");
    }
}
