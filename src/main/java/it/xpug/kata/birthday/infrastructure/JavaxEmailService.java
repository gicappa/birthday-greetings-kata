package it.xpug.kata.birthday.infrastructure;

import it.xpug.kata.birthday.domain.EmailService;
import it.xpug.kata.birthday.domain.EmailTemplate;
import it.xpug.kata.birthday.domain.Employee;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class JavaxEmailService implements EmailService {

    private final String smtpHost;
    private final int smtpPort;

    public JavaxEmailService(String smtpHost, int smtpPort) {
        this.smtpHost = smtpHost;
        this.smtpPort = smtpPort;
    }

    @Override
    public void sendEmailTo(Employee employee, EmailTemplate email) throws MessagingException {

        // Create a mail session
        var props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);

        var session = Session.getInstance(props, null);

        // Construct the message
        var msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(email.sender()));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email.recipient()));
        msg.setSubject(email.subject());
        msg.setText(email.body());

        // Send the message
        Transport.send(msg);

    }
}
