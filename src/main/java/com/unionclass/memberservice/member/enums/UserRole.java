package com.unionclass.memberservice.member.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.unionclass.memberservice.common.exception.BaseException;
import com.unionclass.memberservice.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {

    ROLE_MEMBER("일반회원")
    ;

    private final String userRole;

    @JsonValue
    public String getUserRole() {
        return userRole;
    }

    @JsonCreator
    public static UserRole fromString(String value) {
        for (UserRole userRole : UserRole.values()) {
            if (userRole.userRole.equals(value)) {
                return userRole;
            }
        }
        throw new BaseException(ErrorCode.INVALID_USER_ROLE);
    }
}
