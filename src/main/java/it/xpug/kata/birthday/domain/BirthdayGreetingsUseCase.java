package it.xpug.kata.birthday.domain;

import java.io.IOException;
import java.text.ParseException;
import javax.mail.MessagingException;

public class BirthdayGreetingsUseCase {

    private final EmployeeRepository repo;
    private final EmailService emailService;

    public BirthdayGreetingsUseCase(EmployeeRepository repo, EmailService emailService) {
        this.repo = repo;
        this.emailService = emailService;
    }

    /**
     * The sendGreetings in the code is executing the whole business logic. <br/> The method seems
     * to span in several layer of abstraction: - it is opening the file and reading the file lines
     * - it is parsing the lines and transforming them into employees - it is sending the messages
     * to send the messages <br/> The amount of responsibility of this class is really huge.
     *
     * @param birthDate helper class on dates
     * @param smtpHost  smtp host address
     * @param smtpPort  smtp port address
     * @throws IOException        error in reading the file
     * @throws ParseException     error in parsing a date
     * @throws MessagingException error in sending a message
     */
    public void sendGreetings(
        BirthDate birthDate,
        String smtpHost,
        int smtpPort)
        throws IOException, ParseException, MessagingException {

        for (var employee : repo.findAllEmployees()) {
            if (employee.hasBirthday(birthDate)) {
                composeEmailAndSend(smtpHost, smtpPort, employee);
            }
        }
    }

    private void composeEmailAndSend(String smtpHost, int smtpPort, Employee employee)
        throws MessagingException {
        var emailTemplate = EmailTemplate.fromEmployee(employee);

        sendMessage(smtpHost, smtpPort, employee, emailTemplate);
    }

    /**
     * Sending a message via email.
     *
     * @param smtpHost smtp host address
     * @param smtpPort smtp host port
     * @param employee employee
     * @param email    email template
     * @throws MessagingException error in sending the email.
     */
    private void sendMessage(
        String smtpHost,
        int smtpPort,
        Employee employee, EmailTemplate email) throws MessagingException {
        emailService.sendEmailTo(employee, email);
    }
}
