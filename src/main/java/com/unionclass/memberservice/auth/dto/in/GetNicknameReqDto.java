package com.unionclass.memberservice.auth.dto.in;

import com.unionclass.memberservice.auth.vo.in.GetNicknameReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetNicknameReqDto {

    private String nickname;

    @Builder
    public GetNicknameReqDto(String nickname) { this.nickname = nickname; }

    public static GetNicknameReqDto from(GetNicknameReqVo nicknameReqVo) {
        return GetNicknameReqDto.builder()
                .nickname(nicknameReqVo.getNickname())
                .build();
    }
}
