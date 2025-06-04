package com.unionclass.memberservice.domain.email.util;

import com.unionclass.memberservice.common.exception.BaseException;
import com.unionclass.memberservice.common.exception.ErrorCode;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
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

    public String generateRandomPassword(int length) {

        if (length < 8 || length > 20) {
            log.warn("잘못된 비밀번호 길이 요청: {}자리 → 최소 8자 이상, 최대 20자 이하", length);
            throw new BaseException(ErrorCode.INVALID_PASSWORD_LENGTH);
        }

        SecureRandom random = new SecureRandom();

        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String specialChars = "!@#$%&";

        String allChars = upperCase + lowerCase + digits + specialChars;

        List<Character> passwordChars = new ArrayList<>();

        passwordChars.add(upperCase.charAt(random.nextInt(upperCase.length())));
        passwordChars.add(lowerCase.charAt(random.nextInt(lowerCase.length())));
        passwordChars.add(digits.charAt(random.nextInt(digits.length())));
        passwordChars.add(specialChars.charAt(random.nextInt(specialChars.length())));

        for (int i = 4; i < length; i++) {
            passwordChars.add(allChars.charAt(random.nextInt(allChars.length())));
        }

        Collections.shuffle(passwordChars);

        StringBuilder finalPassword = new StringBuilder(length);
        passwordChars.forEach(finalPassword::append);

        return finalPassword.toString();
    }
}