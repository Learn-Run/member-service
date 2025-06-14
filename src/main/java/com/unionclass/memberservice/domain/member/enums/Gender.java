package com.unionclass.memberservice.domain.member.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.unionclass.memberservice.common.exception.BaseException;
import com.unionclass.memberservice.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Gender {

    GENDER_MALE("남성"),
    GENDER_FEMALE("여성")
    ;

    private final String gender;

    @JsonValue
    public String getGender() {
        return gender;
    }

    @JsonCreator
    public static Gender fromString(String value) {
        for (Gender gender : Gender.values()) {
            if (gender.gender.equals(value)) {
                return gender;
            }
        }
        throw new BaseException(ErrorCode.INVALID_GENDER_VALUE);
    }
}
