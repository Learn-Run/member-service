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
    SUCCESS_CHECK_LOGIN_ID_DUPLICATE("아이디 중복 검사에 성공하였습니다."),
    SUCCESS_CHECK_NICKNAME_DUPLICATE("닉네임 중복 검사에 성공하였습니다."),
    SUCCESS_SEND_TEMPORARY_PASSWORD("임시 비밀번호 발급에 성공하였습니다."),
    SUCCESS_CHANGE_PASSWORD("비밀번호 변경에 성공하였습니다."),
    SUCCESS_SIGN_IN_WITH_OAUTH("소셜 로그인에 성공하였습니다."),
    SUCCESS_SIGN_UP_WITH_OAUTH("소셜 회원가입에 성공하였습니다."),
    SUCCESS_CREATE_AGREEMENT("약관동의 항목 생성에 성공하였습니다."),
    SUCCESS_GET_AGREEMENT("약관동의 항목 단건 조회에 성공하였습니다."),
    SUCCESS_GET_ALL_VALID_AGREEMENT_UUIDS("유효한 약관동의 항목 UUID 전체 조회에 성공하였습니다.")
    ;

    private final String message;
}