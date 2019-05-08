package com.zubarev.websiteavailabilitychecker.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class MailSenderUtil {

    @Value("${mail.subject}")
    private String subject;
    @Value("${mail.text}")
    private String text;

    @Autowired
    JavaMailSender javaMailSender;

    public void sendMessage(String to, String siteName, int statusCode) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject(String.format(subject,siteName));
        message.setText(String.format(text,siteName,statusCode));

        javaMailSender.send(message);
    }
}