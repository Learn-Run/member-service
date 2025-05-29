package com.unionclass.memberservice.auth.dto.out;

import com.unionclass.memberservice.auth.vo.out.SignInResVo;
import com.unionclass.memberservice.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInResDto {

    private String memberUuid;
    private String accessToken;

    @Builder
    public SignInResDto(String memberUuid, String accessToken) {
        this.memberUuid = memberUuid;
        this.accessToken = accessToken;
    }

    public static SignInResDto of(Member member, String accessToken) {
        return SignInResDto.builder()
                .memberUuid(member.getMemberUuid())
                .accessToken(accessToken)
                .build();
    }

    public static SignInResDto of(String memberUuid, String accessToken) {
        return SignInResDto.builder()
                .memberUuid(memberUuid)
                .accessToken(accessToken)
                .build();
    }

    public SignInResVo toVo() {
        return SignInResVo.builder()
                .memberUuid(memberUuid)
                .accessToken(accessToken)
                .build();
    }
}