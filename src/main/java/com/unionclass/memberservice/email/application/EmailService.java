package com.unionclass.memberservice.email.application;

import com.unionclass.memberservice.email.dto.in.EmailCodeReqDto;
import com.unionclass.memberservice.email.dto.in.EmailReqDto;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface EmailService {

    void sendVerificationCode(EmailReqDto emailReqDto) throws MessagingException, UnsupportedEncodingException;
    void verifyEmailCode(EmailCodeReqDto emailCodeReqDto);
    void sendTemporaryPassword(EmailReqDto emailReqDto);
}
