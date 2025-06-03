package com.unionclass.memberservice.domain.auth.vo.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class GetNicknameReqVo {

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;
}
