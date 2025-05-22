package com.unionclass.memberservice.email.util;

import org.springframework.stereotype.Service;

@Service
public class EmailTemplateProvider {

    public String getVerificationCodeByEmailTemplate(String verificationCode) {
        return "안녕하세요. Pick And Learn 입니다.<br/><br/>" +
                "인증코드를 보내드립니다.<br/><br/>" +
                "인증코드: <b>" + verificationCode + "</b>";
    }
}