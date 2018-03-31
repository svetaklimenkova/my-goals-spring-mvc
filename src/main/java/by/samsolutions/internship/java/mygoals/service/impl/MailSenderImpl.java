package by.samsolutions.internship.java.mygoals.service.impl;


import by.samsolutions.internship.java.mygoals.service.MailSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailSenderImpl implements MailSender{
    @Autowired
    private JavaMailSender javaMailSender;

    private static final Logger logger = LoggerFactory.getLogger(MailSenderImpl.class);

    /**
     * Send the message from sender to receiver.
     *
     * @param from sender e-mail
     * @param to receiver
     * @param subject subject
     * @param msg message
     *
     * @return result of sending
     * */
    public boolean send(String from, String to, String subject, String msg) {

        try {
            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(from);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(msg);
            javaMailSender.send(message);
            return true;
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            return false;
        }
    }

}
