package com.unionclass.memberservice.domain.email.dto.in;

import com.unionclass.memberservice.domain.email.vo.in.EmailCodeReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailCodeReqDto {

    private String email;
    private String verificationCode;

    @Builder
    public EmailCodeReqDto(String email, String verificationCode) {
        this.email = email;
        this.verificationCode = verificationCode;
    }

    public static EmailCodeReqDto from(EmailCodeReqVo emailCodeReqVo) {
        return EmailCodeReqDto.builder()
                .email(emailCodeReqVo.getEmail())
                .verificationCode(emailCodeReqVo.getVerificationCode())
                .build();
    }
}
