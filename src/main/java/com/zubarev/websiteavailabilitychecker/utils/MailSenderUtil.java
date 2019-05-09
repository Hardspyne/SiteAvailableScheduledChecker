package com.zubarev.websiteavailabilitychecker.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

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

    public void sendMessageWithAttachment(String to, String siteName, int statusCode, String pathToFile) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(String.format(subject,siteName));
        helper.setText(String.format(text,siteName,statusCode));

        FileSystemResource file
                = new FileSystemResource(new File(pathToFile));
        helper.addAttachment("screen.png", file);

        javaMailSender.send(message);
    }
}