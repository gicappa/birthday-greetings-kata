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
     * @throws IOException        error in reading the file
     * @throws ParseException     error in parsing a date
     * @throws MessagingException error in sending a message
     */
    public void sendGreetings(BirthDate birthDate)
        throws IOException, ParseException, MessagingException {

        for (var employee : repo.findAllEmployees()) {

            if (!employee.hasBirthday(birthDate)) {
                continue;
            }

            var email = EmailTemplate.fromEmployee(employee);
            emailService.sendEmailTo(employee, email);
        }
    }
}
