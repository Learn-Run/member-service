package com.unionclass.memberservice.email.dto.in;

import com.unionclass.memberservice.email.vo.in.EmailReqVo;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class EmailReqDto {

    private String email;

    @Builder
    public EmailReqDto(String email) {
        this.email = email;
    }

    public static EmailReqDto from(EmailReqVo emailReqVo) {
        return EmailReqDto.builder()
                .email(emailReqVo.getEmail())
                .build();
    }
}
