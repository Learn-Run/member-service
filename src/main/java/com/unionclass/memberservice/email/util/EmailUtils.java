package com.unionclass.memberservice.email.util;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;

@Component
@RequiredArgsConstructor
public class EmailUtils {

    @Value("${spring.mail.name}")
    private String name;
    @Value("${spring.mail.address}")
    private String address;

    private final JavaMailSender mailSender;

    public String generateRandomCode() {
        return String.valueOf(100000 + new SecureRandom().nextInt(900000));
    }

    public MimeMessage createMessage(
            String recipient, String title, String content
    ) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        message.setFrom(new InternetAddress(address, name));
        message.setRecipients(Message.RecipientType.TO, recipient);
        message.setSubject(title);
        message.setText(content, "UTF-8", "html");

        return message;
    }
}
