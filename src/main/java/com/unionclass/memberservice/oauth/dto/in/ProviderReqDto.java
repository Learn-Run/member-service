package com.unionclass.memberservice.oauth.dto.in;

import com.unionclass.memberservice.oauth.enums.Provider;
import com.unionclass.memberservice.oauth.vo.in.ProviderReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ProviderReqDto {

    private Provider provider;
    private String providerAccountId;

    @Builder
    public ProviderReqDto(Provider provider, String providerAccountId) {
        this.provider = provider;
        this.providerAccountId = providerAccountId;
    }

    public static ProviderReqDto from(ProviderReqVo oAuthProviderReqVo) {
        return ProviderReqDto.builder()
                .provider(oAuthProviderReqVo.getProvider())
                .providerAccountId(oAuthProviderReqVo.getProviderAccountId())
                .build();
    }
}
