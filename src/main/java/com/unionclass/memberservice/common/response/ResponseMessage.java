package com.unionclass.memberservice.common.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseMessage {

    SIGN_UP_SUCCESS("회원가입에 성공하였습니다."),
    SIGN_IN_SUCCESS("로그인에 성공하였습니다.");

    private final String message;

}
