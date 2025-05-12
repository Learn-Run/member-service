package com.unionclass.memberservice.auth.dto.in;

import com.unionclass.memberservice.auth.vo.in.SignUpReqVo;
import com.unionclass.memberservice.member.entity.Member;
import com.unionclass.memberservice.member.enums.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class SignUpReqDto {

    private String loginId;
    private String password;
    private String email;
    private LocalDate birthDate;
    private Gender gender;
    private String nickname;

    @Builder
    public SignUpReqDto(
            String loginId, String password, String email,
            LocalDate birthDate, Gender gender, String nickname
    ) {
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.nickname = nickname;
    }

    public static SignUpReqDto from(SignUpReqVo signUpReqVo) {
        return SignUpReqDto.builder()
                .loginId(signUpReqVo.getLoginId())
                .password(signUpReqVo.getPassword())
                .email(signUpReqVo.getEmail())
                .birthDate(signUpReqVo.getBirthDate())
                .gender(signUpReqVo.getGender())
                .nickname(signUpReqVo.getNickname())
                .build();
    }

    public Member toEntity(Long memberUuid, String inputPassword) {
        return Member.builder()
                .memberUuid(memberUuid)
                .loginId(loginId)
                .password(inputPassword)
                .email(email)
                .birthDate(birthDate)
                .gender(gender)
                .nickname(nickname)
                .deletedStatus(false)
                .build();
    }
}