package it.xpug.kata.birthday.domain;

/**
 * The representation of a greeting email
 *
 * @param sender    the sender address
 * @param recipient the recipient address
 * @param subject   the email subject
 * @param body      the email body
 */
public record GreetingEmail(String sender, String recipient, String subject, String body) {

    public static GreetingEmail fromEmployee(Employee employee) {
        return new GreetingEmail(
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
