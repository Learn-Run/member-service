package com.unionclass.memberservice.member.vo.in;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ChangePasswordReqVo {

    private String currentPassword;

    @Size(min = 8, message = "비밀번호는 8글자 이상으로 입력해주세요.")
    @Size(max = 20, message = "비밀번호은 20글자 이하로 입력해주세요.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).*$",
            message = "대문자를 포함한 영문, 숫자, 특수문자를 최소 1글자 이상 포함하여 작성해주세요.)"
    )
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String newPassword;
}
