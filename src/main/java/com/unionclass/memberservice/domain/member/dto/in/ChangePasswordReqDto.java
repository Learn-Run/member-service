package com.unionclass.memberservice.domain.member.dto.in;

import com.unionclass.memberservice.domain.member.vo.in.ChangePasswordReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ChangePasswordReqDto {

    private String memberUuid;
    private String currentPassword;
    private String newPassword;

    @Builder
    public ChangePasswordReqDto(String memberUuid, String currentPassword, String newPassword) {
        this.memberUuid = memberUuid;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
    }

    public static ChangePasswordReqDto of(String memberUuid, ChangePasswordReqVo passwordReqVo) {
        return ChangePasswordReqDto.builder()
                .memberUuid(memberUuid)
                .currentPassword(passwordReqVo.getCurrentPassword())
                .newPassword(passwordReqVo.getNewPassword())
                .build();
    }
}
