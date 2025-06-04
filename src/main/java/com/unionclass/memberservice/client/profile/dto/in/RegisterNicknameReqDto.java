package com.unionclass.memberservice.client.profile.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterNicknameReqDto {

    private String memberUuid;
    private String nickname;

    @Builder
    public RegisterNicknameReqDto(String memberUuid, String nickname) {
        this.memberUuid = memberUuid;
        this.nickname = nickname;
    }

    public static RegisterNicknameReqDto of(String memberUuid, String nickname) {
        return RegisterNicknameReqDto.builder()
                .memberUuid(memberUuid)
                .nickname(nickname)
                .build();
    }
}
