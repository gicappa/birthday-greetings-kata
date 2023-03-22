package it.xpug.kata.birthday.domain;

import javax.mail.MessagingException;

public interface EmailService {

    void sendEmailTo(EmailTemplate emailTemplate) throws MessagingException;
}
