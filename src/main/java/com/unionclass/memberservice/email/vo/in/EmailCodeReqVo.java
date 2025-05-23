package com.unionclass.memberservice.email.vo.in;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class EmailCodeReqVo {

    @Email(message = "올바른 이메일 형식으로 입력해주세요.")
    private String email;

    @Pattern(regexp = "^\\d{6}$", message = "인증 코드는 6자리 숫자여야 합니다.")
    private String verificationCode;
}
