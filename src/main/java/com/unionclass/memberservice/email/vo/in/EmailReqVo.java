package com.unionclass.memberservice.email.vo.in;

import jakarta.validation.constraints.Email;
import lombok.Getter;

@Getter
public class EmailReqVo {

    @Email(message = "올바른 이메일 형식으로 입력해주세요.")
    private String email;
}