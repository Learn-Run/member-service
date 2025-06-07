package com.unionclass.memberservice.domain.email.application;

import com.unionclass.memberservice.domain.email.dto.in.EmailCodeReqDto;
import com.unionclass.memberservice.domain.email.dto.in.EmailReqDto;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface EmailService {

    void sendVerificationCode(EmailReqDto emailReqDto) throws MessagingException, UnsupportedEncodingException;
    void verifyEmailCode(EmailCodeReqDto emailCodeReqDto);
    void sendTemporaryPasswordWithLock(EmailReqDto emailReqDto);
}
