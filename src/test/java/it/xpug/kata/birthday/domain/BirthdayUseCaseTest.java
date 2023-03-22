package it.xpug.kata.birthday.domain;

import static it.xpug.kata.birthday.ClockTestHelper.clockAt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import javax.mail.MessagingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BirthdayUseCaseTest {

    private BirthdayUseCase birthdayUseCase;
    private EmployeeRepository repo;
    private EmailService emailService;
    private Employee emma;

    @BeforeEach
    void beforeEach() {
        repo = mock(EmployeeRepository.class);
        emailService = mock(EmailService.class);

        var jon = new Employee(
            "Jon", "Doe", "1972/10/15", "jon.doe@gmail.com");
        emma = new Employee(
            "Emma", "Stone", "2011/08/03", "emma.stone@gmail.com");

        when(repo.findAllEmployees()).thenReturn(List.of(jon, emma));

        birthdayUseCase = new BirthdayUseCase(
            repo,
            emailService,
            clockAt(2023, 8, 3));
    }

    @Test
    void it_find_all_employees_when_it_finds_a_birthday() {
        birthdayUseCase.sendGreetings();

        verify(repo).findAllEmployees();
    }

    @Test
    void it_sends_an_email_when_it_finds_a_birthday() throws MessagingException {
        birthdayUseCase.sendGreetings();

        verify(emailService).sendEmailTo(EmailTemplate.fromEmployee(emma));
    }
}
