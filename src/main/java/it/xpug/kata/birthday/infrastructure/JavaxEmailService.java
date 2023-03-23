package it.xpug.kata.birthday.infrastructure;

import it.xpug.kata.birthday.domain.EmailService;
import it.xpug.kata.birthday.domain.GreetingEmail;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Concrete implementation of an email service capable
 * of sending greeting emails
 */
public class JavaxEmailService implements EmailService {

    private final String smtpHost;
    private final int smtpPort;

    public JavaxEmailService(String smtpHost, int smtpPort) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
    }

    @Override
    public void sendEmailTo(GreetingEmail email) throws MessagingException {
        MimeMessage msg = buildEmailMessage(email, createEmailSession());

        Transport.send(msg);
    }

    private static MimeMessage buildEmailMessage(GreetingEmail email, Session session)
        throws MessagingException {

        var msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(email.sender()));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email.recipient()));
        msg.setSubject(email.subject());
        msg.setText(email.body());
        return msg;
    }

    private Session createEmailSession() {
        var props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);
        return Session.getInstance(props, null);
    }
}
