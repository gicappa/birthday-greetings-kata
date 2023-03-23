package it.xpug.kata.birthday.domain;

/**
 * The interface is abstracting the email service capable to send greetings Email
 */
public interface EmailService {

    void sendEmailTo(Email email);
}
