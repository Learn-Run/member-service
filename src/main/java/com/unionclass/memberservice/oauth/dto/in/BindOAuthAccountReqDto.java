package com.unionclass.memberservice.oauth.dto.in;

import com.unionclass.memberservice.oauth.entity.MemberOAuth;
import com.unionclass.memberservice.oauth.enums.Provider;
import com.unionclass.memberservice.oauth.vo.in.ProviderReqVo;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BindOAuthAccountReqDto {

    private String memberUuid;
    private Provider provider;
    private String providerAccountId;

    @Builder
    public BindOAuthAccountReqDto(String memberUuid, Provider provider, String providerAccountId) {
        this.memberUuid = memberUuid;
        this.provider = provider;
        this.providerAccountId = providerAccountId;
    }

    public static BindOAuthAccountReqDto of(String memberUuid, ProviderReqVo providerReqVo) {
        return BindOAuthAccountReqDto.builder()
                .memberUuid(memberUuid)
                .provider(providerReqVo.getProvider())
                .providerAccountId(providerReqVo.getProviderAccountId())
                .build();
    }

    public MemberOAuth toEntity() {
        return MemberOAuth.builder()
                .memberUuid(memberUuid)
                .provider(provider)
                .providerAccountId(providerAccountId)
                .build();
    }
}
