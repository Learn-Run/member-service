package com.unionclass.memberservice.common.response;

import org.springframework.http.HttpStatus;

public record BaseResponseEntity<T>(HttpStatus httpStatus, int code, String message, boolean success, T result) {

    public BaseResponseEntity(String message, T result) {this(HttpStatus.OK, 200, message, true, result);}

    public BaseResponseEntity(String message) {
        this(HttpStatus.OK, 200, message, true, null);
    }
}

