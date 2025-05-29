package com.unionclass.memberservice.auth.vo.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AccountReqVo {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String account;
}
