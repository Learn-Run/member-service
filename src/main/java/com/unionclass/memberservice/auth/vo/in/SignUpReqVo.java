package com.unionclass.memberservice.auth.vo.in;

import com.unionclass.memberservice.member.enums.Gender;
import com.unionclass.memberservice.member.enums.UserRole;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SignUpReqVo {

    @Size(min = 4, message = "아이디는 4글자 이상으로 입력해주세요.")
    @Size(max = 20, message = "아이디는 20글자 이하로 입력해주세요.")
    @NotBlank(message = "아이디를 입력해주세요.")
    private String account;

    @Size(min = 8, message = "비밀번호는 8글자 이상으로 입력해주세요.")
    @Size(max = 20, message = "비밀번호은 20글자 이하로 입력해주세요.")
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*]).*$",
            message = "대문자를 포함한 영문, 숫자, 특수문자를 최소 1글자 이상 포함하여 작성해주세요.)"
    )
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;

    @Email(message = "올바른 이메일 형식으로 입력해주세요.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotNull(message = "이름을 입력해주세요.")
    private String name;
    @NotNull(message = "생년월일을 입력해주세요.")
    private LocalDate birthDate;

    @NotNull(message = "성별을 입력해주세요. (남성 또는 여성)")
    private Gender gender;

    @Size(min = 2, message = "닉네임은 2글자 이상으로 입력해주세요.")
    @Size(max = 12, message = "닉네임은 8글자 이하로 입력해주세요.")
    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickname;
    private UserRole userRole;
}
