package com.unionclass.memberservice.common.response;

import org.springframework.http.HttpStatus;

public record BaseResponseEntity<T>(HttpStatus httpStatus, boolean isSuccess, ResponseMessage responseMessage, int code, T result) {

    public BaseResponseEntity(ResponseMessage responseMessage, T result) {
        this(HttpStatus.OK, true, responseMessage, 200, result);
    }

    public BaseResponseEntity(ResponseMessage responseMessage) {
        this(HttpStatus.OK, true, responseMessage, 200, null);
    }
}

