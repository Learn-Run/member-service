package com.unionclass.memberservice.member.dto.in;

import com.unionclass.memberservice.member.vo.in.ChangeNicknameReqVo;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChangeNicknameReqDto {

    private String memberUuid;
    private String nickname;

    @Builder
    public ChangeNicknameReqDto(String memberUuid, String nickname) {
        this.memberUuid = memberUuid;
        this.nickname = nickname;
    }

    public static ChangeNicknameReqDto of(String memberUuid, ChangeNicknameReqVo changeNicknameReqVo) {
        return ChangeNicknameReqDto.builder()
                .memberUuid(memberUuid)
                .nickname(changeNicknameReqVo.getNickname())
                .build();
    }
}
