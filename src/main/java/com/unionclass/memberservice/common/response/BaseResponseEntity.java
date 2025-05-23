package com.unionclass.memberservice.common.response;

import org.springframework.http.HttpStatus;

public record BaseResponseEntity<T>(HttpStatus httpStatus, boolean isSuccess, String responseMessage, int code, T result) {

    public BaseResponseEntity(String responseMessage, T result) {
        this(HttpStatus.OK, true, responseMessage, 200, result);
    }

    public BaseResponseEntity(String responseMessage) {
        this(HttpStatus.OK, true, responseMessage, 200, null);
    }
}

