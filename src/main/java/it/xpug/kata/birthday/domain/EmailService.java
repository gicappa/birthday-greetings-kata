package it.xpug.kata.birthday.domain;

import javax.mail.MessagingException;

/**
 * The interface is abstracting the email service
 * capable to send greetings Email
 */
public interface EmailService {

    void sendEmailTo(GreetingEmail email) throws MessagingException;
}
