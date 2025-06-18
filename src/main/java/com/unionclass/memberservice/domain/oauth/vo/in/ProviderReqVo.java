package com.unionclass.memberservice.domain.oauth.vo.in;

import com.unionclass.memberservice.domain.oauth.enums.Provider;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProviderReqVo {

    @NotNull(message = "소셜 로그인 제공자를 입력해주세요. (KAKAO)")
    private Provider provider;

    @NotBlank(message = "소셜 계정 식별자를 입력해주세요.")
    private String providerAccountId;
}
