package it.xpug.kata.birthday.domain;

public record EmailTemplate(String sender, String recipient, String subject, String body) {

    public static EmailTemplate fromEmployee(Employee employee) {
        return new EmailTemplate(
            "sender@here.com",
            employee.email(),
            "Happy Birthday!",
            bodyTemplate(employee));
    }

    private static String bodyTemplate(Employee employee) {
        return """
            Happy Birthday, dear %s!
            """
            .formatted(employee.firstName());
    }
}
