package by.samsolutions.internship.java.mygoals.service;

public interface MailSender {
    boolean send(String from, String to, String subject, String msg);
}
