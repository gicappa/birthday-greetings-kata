package it.xpug.kata.birthday.domain;

/**
 * The representation of a greeting email
 *
 * @param sender    the sender address
 * @param recipient the recipient address
 * @param subject   the email subject
 * @param body      the email body
 */
public record Email(String sender, String recipient, String subject, String body) {

    /**
     * The usage of a static method is a smell on the fact that
     * a new abstraction could be needed to compose an email
     * out of an employee model.
     * For the moment the logic seems so tiny that the creation
     * of a new class seems not useful.
     *
     * @param employee the employee where to take the data from
     * @return the greeting email
     */
    public static Email composeFrom(Employee employee) {
        return new Email(
            "sender@here.com", employee.email(),
            "Happy Birthday!", bodyTemplate(employee));
    }

    private static String bodyTemplate(Employee employee) {
        return """
            Happy Birthday, dear %s!
            """
            .formatted(employee.firstName());
    }
}
