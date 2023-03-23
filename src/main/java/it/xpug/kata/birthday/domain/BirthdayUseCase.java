package it.xpug.kata.birthday.domain;

import java.time.Clock;
import java.time.LocalDate;
import javax.mail.MessagingException;

public class BirthdayUseCase {

    private final EmployeeRepo repo;
    private final EmailService emailService;
    private final Clock clock;

    /**
     * @param repo         the employee repository to interact with storage
     * @param emailService the email service to send emails
     * @param clock        the clock to set the current time
     */
    public BirthdayUseCase(EmployeeRepo repo, EmailService emailService, Clock clock) {
        this.repo = repo;
        this.emailService = emailService;
        this.clock = clock;
    }

    /**
     * The sendGreetings method filter all the employees who have their birthday today
     *
     * @throws RuntimeException error while opening a file or parsing the csv
     */
    public void sendGreetings() {
        try {
            for (var employee : repo.findAllEmployees()) {

                if (!employee.hasBirthday(today())) {
                    continue;
                }

                emailService.sendEmailTo(Email.composeFrom(employee));
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @return today date
     */
    private LocalDate today() {
        return LocalDate.now(clock);
    }
}
