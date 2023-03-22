package it.xpug.kata.birthday.greetings;

import it.xpug.kata.birthday.domain.Employee;
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

    /**
     * The sendGreetings in the code is executing the whole business logic.
     * <br/>
     * The method seems to span in several layer of abstraction:
     * - it is opening the file and reading the file lines
     * - it is parsing the lines and transforming them into employees
     * - it is sending the messages to send the messages
     * <br/>
     * The amount of responsibility of this class is really huge.
     *
     * @param fileName file name to open
     * @param birthDate helper class on dates
     * @param smtpHost smtp host address
     * @param smtpPort smtp port address
     * @throws IOException error in reading the file
     * @throws ParseException error in parsing a date
     * @throws MessagingException error in sending a message
     */
    public void sendGreetings(String fileName, BirthDate birthDate, String smtpHost, int smtpPort)
        throws IOException, ParseException, MessagingException {

        var in = new BufferedReader(new FileReader(fileName));

        String str = in.readLine();
        while ((str = in.readLine()) != null) {
            var employeeData = str.split(", ");

            var employee = new Employee(
                employeeData[1],
                employeeData[0],
                employeeData[2],
                employeeData[3]);

            if (employee.hasBirthday(birthDate)) {

                var recipient = employee.email();
                var body =
                    "Happy Birthday, dear %NAME%!".replace(
                        "%NAME%",
                        employee.firstName());

                var subject = "Happy Birthday!";

                sendMessage(smtpHost, smtpPort, "sender@here.com", subject, body, recipient);
            }
        }
    }

    /**
     * Sending a message via email.
     *
     * @param smtpHost smtp host address
     * @param smtpPort smtp host port
     * @param sender email sender
     * @param subject email subject
     * @param body email body
     * @param recipient email recipient
     * @throws MessagingException error in sending the email.
     */
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
