package it.xpug.kata.birthday.infrastructure;

import it.xpug.kata.birthday.domain.Email;
import it.xpug.kata.birthday.domain.EmailService;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Concrete implementation of an email service capable of sending greeting emails
 */
public class JavaxEmailService implements EmailService {

    private final String smtpHost;
    private final int smtpPort;

    public JavaxEmailService(String smtpHost, int smtpPort) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
    }

    @Override
    public void sendEmailTo(Email email) {

        try {
            Transport.send(buildEmailMessage(email, createEmailSession()));
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private MimeMessage buildEmailMessage(Email email, Session session) {
        try {
            var msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(email.sender()));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email.recipient()));
            msg.setSubject(email.subject());
            msg.setText(email.body());
            return msg;
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    private Session createEmailSession() {
        var props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);
        return Session.getInstance(props, null);
    }
}
