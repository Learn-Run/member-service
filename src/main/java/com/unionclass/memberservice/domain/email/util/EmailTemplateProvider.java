package com.unionclass.memberservice.domain.email.util;

import org.springframework.stereotype.Service;

@Service
public class EmailTemplateProvider {

    public String getVerificationCodeByEmailTemplate(String verificationCode) {
        return "안녕하세요. Pick And Learn 입니다.<br/><br/>" +
                "인증코드를 보내드립니다.<br/><br/>" +
                "인증코드: <b>" + verificationCode + "</b>";
    }

    public String getTemporaryPasswordByEmailTemplate(String temporaryPassword) {
        return "안녕하세요. Pick And Learn 입니다.<br/><br/>" +
                "요청하신 <b>임시 비밀번호</b>를 아래와 같이 안내드립니다.<br/><br/>" +
                "임시 비밀번호: <b>" + temporaryPassword + "</b><br/><br/>" +
                "이 비밀번호는 기존 비밀번호가 아닌, <b>회원님의 비밀번호로 자동 설정</b>된 상태입니다.<br/>" +
                "해당 비밀번호로 로그인하실 수 있으며, <b>유효기간은 따로 없지만</b> 보안을 위해 <b>비밀번호를 빠르게 변경하실 것을 권장</b>드립니다.<br/><br/>" +
                "비밀번호 변경은 로그인 후 <b>마이페이지 &gt; 비밀번호 변경</b> 메뉴에서 가능합니다.<br/><br/>" +
                "감사합니다.";
    }
}