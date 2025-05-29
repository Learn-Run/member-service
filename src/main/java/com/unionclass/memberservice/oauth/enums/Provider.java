package com.unionclass.memberservice.oauth.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.unionclass.memberservice.common.exception.BaseException;
import com.unionclass.memberservice.common.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Provider {

    PROVIDER_KAKAO("KAKAO")
    ;

    private final String provider;

    @JsonValue
    public String getProvider() { return provider; }

    @JsonCreator
    public static Provider fromString(String value) {
        for (Provider provider : Provider.values()) {
            if (provider.provider.equals(value)) {
                return  provider;
            }
        }
        throw new BaseException(ErrorCode.INVALID_OAUTH_PROVIDER_VALUE);
    }
}
