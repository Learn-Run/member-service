package com.unionclass.memberservice.domain.auth.vo.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignInReqVo {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String loginId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
}
