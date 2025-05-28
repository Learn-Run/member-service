package com.unionclass.memberservice.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 888 : internal server error
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, 888, "서버에서 요청을 처리하지 못했습니다."),

    // 999 : validation error

    /**
     * 1000 ~ 1999 : member service error
     */
    // auth : 1000 ~ 1099
    NO_SIGN_IN(HttpStatus.UNAUTHORIZED, false, 1001, "로그인을 먼저 진행해주세요"),
    FAILED_TO_SIGN_UP(HttpStatus.INTERNAL_SERVER_ERROR, false, 1002, "회원가입에 실패하였습니다."),
    FAILED_TO_SIGN_IN(HttpStatus.INTERNAL_SERVER_ERROR, false, 1003, "로그인에 실패하였습니다."),

    // member : 1100 ~ 1199
    NO_EXIST_MEMBER(HttpStatus.NOT_FOUND, false, 1100, "해당 회원을 찾을 수 없습니다."),
    INVALID_GENDER_VALUE(HttpStatus.BAD_REQUEST, false, 1101, "유효하지 않은 성별 정보입니다."),
    INVALID_USER_ROLE(HttpStatus.BAD_REQUEST, false, 1102, "유효하지 않은 유저 권한입니다."),
    EMAIL_ENCODING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, false, 1103, "시스템 문자 인코딩 오류로 메일을 보낼 수 없습니다."),
    EMAIL_SEND_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, false, 1104, "메일 서버 오류로 인해 전송에 실패했습니다."),
    EMAIL_CODE_INVALID(HttpStatus.BAD_REQUEST, false, 1105, "유효하지 않은 인증코드입니다."),
    EMAIL_CODE_EXPIRED(HttpStatus.BAD_REQUEST, false, 1106, "인증코드가 만료되었습니다."),
    EMAIL_ALREADY_EXISTS(HttpStatus.CONFLICT, false, 1107, "이미 등록된 이메일입니다."),
    LOGIN_ID_ALREADY_EXISTS(HttpStatus.CONFLICT, false, 1108, "이미 등록된 아이디입니다."),
    NICKNAME_ALREADY_EXISTS(HttpStatus.CONFLICT, false, 1109, "이미 등록된 닉네임입니다."),
    INVALID_PASSWORD_RULE(HttpStatus.BAD_REQUEST, false, 1110, "length 에 4이상의 수를 입력해주세요."),
    INVALID_EMAIL_TITLE_VALUE(HttpStatus.BAD_REQUEST, false, 1111, "유효하지 않은 이메일 제목 정보입니다."),
    INVALID_PASSWORD_LENGTH(HttpStatus.BAD_REQUEST, false, 1112, "비밀번호는 8자 이상 20자 이하로 입력해주세요."),
    INVALID_CURRENT_PASSWORD(HttpStatus.BAD_REQUEST, false, 1113, "현재 비밀번호가 일치하지 않습니다."),
    INVALID_OAUTH_PROVIDER_VALUE(HttpStatus.BAD_REQUEST, false, 1114, "유효하지 않은 OAuth Provider 입니다."),
    NO_EXIST_OAUTH_MEMBER(HttpStatus.NOT_FOUND, false, 1115, "해당 소셜 계정과 연동된 회원이 없습니다. 회원가입을 먼저 진행해주세요."),
    OAUTH_ACCOUNT_ALREADY_BOUND(HttpStatus.CONFLICT, false, 1116, "해당 OAuth 계정은 이미 연동되어 있습니다.")
    ;

    /**
     * 2000 : post service error
     */

    /**
     * 3000 : order service error
     */

    /**
     * 4000 : chat service error
     */

    /**
     * 5000 : notice service error
     */

    private final HttpStatus httpStatus;
    private final boolean isSuccess;
    private final int code;
    private final String message;
}
