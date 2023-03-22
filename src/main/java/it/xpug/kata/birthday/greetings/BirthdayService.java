package it.xpug.kata.birthday.greetings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class BirthdayService {

    public void sendGreetings(String fileName, XDate xDate, String smtpHost, int smtpPort)
        throws IOException, ParseException, MessagingException {

        var in = new BufferedReader(new FileReader(fileName));

        String str = "";
        str = in.readLine();
        while ((str = in.readLine()) != null) {
            var employeeData = str.split(", ");

            var employee = new Employee(
                employeeData[1],
                employeeData[0],
                employeeData[2],
                employeeData[3]);

            if (employee.isBirthday(xDate)) {

                var recipient = employee.getEmail();
                var body =
                    "Happy Birthday, dear %NAME%!".replace(
                        "%NAME%",
                        employee.getFirstName());

                var subject = "Happy Birthday!";

                sendMessage(smtpHost, smtpPort, "sender@here.com", subject, body, recipient);
            }
        }
    }

    private void sendMessage(
        String smtpHost,
        int smtpPort,
        String sender,
        String subject,
        String body,
        String recipient) throws MessagingException {

        // Create a mail session
        var props = new Properties();
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", "" + smtpPort);

        var session = Session.getInstance(props, null);

        // Construct the message
        var msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(sender));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        msg.setSubject(subject);
        msg.setText(body);

        // Send the message
        Transport.send(msg);
    }
}
