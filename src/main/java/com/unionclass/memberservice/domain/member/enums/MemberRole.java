package com.unionclass.memberservice.domain.member.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.unionclass.memberservice.common.exception.BaseException;
import com.unionclass.memberservice.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberRole {

    ROLE_MEMBER("일반회원")
    ;

    private final String userRole;

    @JsonValue
    public String getUserRole() {
        return userRole;
    }

    @JsonCreator
    public static MemberRole fromString(String value) {
        for (MemberRole memberRole : MemberRole.values()) {
            if (memberRole.userRole.equals(value)) {
                return memberRole;
            }
        }
        throw new BaseException(ErrorCode.INVALID_USER_ROLE);
    }
}
