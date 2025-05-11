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

    // member : 1100 ~ 1199
    NO_EXIST_MEMBER(HttpStatus.NOT_FOUND, false, 1100, "해당 UUID 를 가진 회원이 존재하지 않습니다.");

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
