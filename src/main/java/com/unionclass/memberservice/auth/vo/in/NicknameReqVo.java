package com.unionclass.memberservice.auth.vo.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class NicknameReqVo {

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;
}
