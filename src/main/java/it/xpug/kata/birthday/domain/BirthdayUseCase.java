package it.xpug.kata.birthday.domain;

import java.time.Clock;
import java.time.LocalDate;
import javax.mail.MessagingException;

public class BirthdayUseCase {

    private final EmployeeRepository repo;
    private final EmailService emailService;
    private final Clock clock;

    public BirthdayUseCase(
        EmployeeRepository repo,
        EmailService emailService,
        Clock clock) {

        this.repo = repo;
        this.emailService = emailService;
        this.clock = clock;
    }

    /**
     * The sendGreetings in the code is executing the whole business logic. <br/> The method seems
     * to span in several layer of abstraction: - it is opening the file and reading the file lines
     * - it is parsing the lines and transforming them into employees - it is sending the messages
     * to send the messages <br/> The amount of responsibility of this class is really huge.
     *
     * @throws RuntimeException error while opening a file or parsing the csv
     */
    public void sendGreetings() {
        try {
            for (var employee : repo.findAllEmployees()) {

                if (!employee.hasBirthday(today())) {
                    continue;
                }

                var email = EmailTemplate.fromEmployee(employee);
                emailService.sendEmailTo(employee, email);
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private LocalDate today() {
        return LocalDate.now(clock);
    }
}
