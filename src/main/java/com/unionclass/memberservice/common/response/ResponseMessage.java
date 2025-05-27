package com.unionclass.memberservice.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseMessage {

    SUCCESS_SIGN_UP("회원가입에 성공하였습니다."),
    SUCCESS_SIGN_IN("로그인에 성공하였습니다."),
    SUCCESS_SEND_VERIFICATION_EMAIL("메일 인증코드 발송에 성공하였습니다."),
    SUCCESS_VERIFY_EMAIL_CODE("메일 인증코드 검증에 성공하였습니다."),
    SUCCESS_CHECK_EMAIL_DUPLICATE("이메일 중복 검사에 성공하였습니다."),
    SUCCESS_CHECK_LOGIN_ID_DUPLICATE("아이디 중복 검사에 성공하였습니다.")
    ;

    private final String message;
}