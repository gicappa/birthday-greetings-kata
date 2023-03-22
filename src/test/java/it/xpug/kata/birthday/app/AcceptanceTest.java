package it.xpug.kata.birthday.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;
import it.xpug.kata.birthday.domain.BirthdayGreetingsUseCase;
import it.xpug.kata.birthday.infrastructure.CsvEmployeeRepository;
import it.xpug.kata.birthday.infrastructure.JavaxEmailService;
import java.io.FileNotFoundException;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class AcceptanceTest {

    private static final int NONSTANDARD_PORT = 9999;
    private BirthdayGreetingsUseCase birthdayGreetingsUseCase;
    private SimpleSmtpServer mailServer;
    private Clock clock;

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        mailServer = SimpleSmtpServer.start(NONSTANDARD_PORT);

        birthdayGreetingsUseCase =
            new BirthdayGreetingsUseCase(
                new CsvEmployeeRepository("employee_data.txt"),
                new JavaxEmailService("localhost", NONSTANDARD_PORT));

        clock = clockAt(2008, 10, 8);
    }

    private Clock clockAt(int year, int month, int day) {
        return Clock.fixed(
            LocalDate.of(year, month, day)
                .atTime(0, 0)
                .toInstant(ZoneOffset.UTC),
            ZoneId.of("CET"));
    }

    @Test
    public void willSendGreetings_whenItsSomebodysBirthday() {
        birthdayGreetingsUseCase.sendGreetings(clock);
        assertEquals(1, mailServer.getReceivedEmailSize(), "message not sent?");
    }

    @Test
    public void willHaveSubject_whenReceivingAnEmail() {
        birthdayGreetingsUseCase.sendGreetings(clock);
        var message = (SmtpMessage) mailServer.getReceivedEmail().next();
        assertEquals("Happy Birthday!", message.getHeaderValue("Subject"));
    }

    @Test
    public void willHaveBody_whenReceivingAnEmail() {
        birthdayGreetingsUseCase.sendGreetings(clock);
        var message = (SmtpMessage) mailServer.getReceivedEmail().next();

        assertEquals("Happy Birthday, dear John!", message.getBody());
    }

    @Test
    public void willHaveRecipient_whenReceivingAnEmail() {
        birthdayGreetingsUseCase.sendGreetings(clock);
        var message = (SmtpMessage) mailServer.getReceivedEmail().next();

        var recipients = message.getHeaderValues("To");
        assertEquals(1, recipients.length);
        assertEquals("john.doe@foobar.com", recipients[0]);
    }

    @Test
    public void willNotSendEmailsWhenNobodysBirthday() {
        birthdayGreetingsUseCase.sendGreetings(clockAt(2008, 1, 1));

        assertEquals(0, mailServer.getReceivedEmailSize(), "what? messages?");
    }

    @AfterEach
    public void tearDown() throws Exception {
        mailServer.stop();

        Thread.sleep(200);
    }

}
