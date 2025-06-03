package com.unionclass.memberservice.domain.member.dto.in;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResetPasswordReqDto {

    private String email;
    private String temporaryPassword;

    @Builder
    public ResetPasswordReqDto(String email, String temporaryPassword) {
        this.email = email;
        this.temporaryPassword = temporaryPassword;
    }

    public static ResetPasswordReqDto of(String email, String temporaryPassword) {
        return ResetPasswordReqDto.builder()
                .email(email)
                .temporaryPassword(temporaryPassword)
                .build();
    }
}
