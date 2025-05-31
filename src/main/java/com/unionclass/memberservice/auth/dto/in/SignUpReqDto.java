package com.unionclass.memberservice.auth.dto.in;

import com.unionclass.memberservice.auth.vo.in.SignUpReqVo;
import com.unionclass.memberservice.member.entity.Member;
import com.unionclass.memberservice.member.enums.Gender;
import com.unionclass.memberservice.member.enums.UserRole;
import com.unionclass.memberservice.oauth.dto.in.SignUpWithOAuthReqDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class SignUpReqDto {

    private String loginId;
    private String password;
    private String email;
    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private String nickname;
    private UserRole userRole;

    @Builder
    public SignUpReqDto(
            String loginId, String password, String email, String name,
            LocalDate birthDate, Gender gender, String nickname, UserRole userRole
    ) {
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.nickname = nickname;
        this.userRole = userRole;
    }

    public static SignUpReqDto from(SignUpReqVo signUpReqVo) {
        return SignUpReqDto.builder()
                .loginId(signUpReqVo.getLoginId())
                .password(signUpReqVo.getPassword())
                .email(signUpReqVo.getEmail())
                .name(signUpReqVo.getName())
                .birthDate(signUpReqVo.getBirthDate())
                .gender(signUpReqVo.getGender())
                .nickname(signUpReqVo.getNickname())
                .userRole(signUpReqVo.getUserRole())
                .build();
    }

    public static SignUpReqDto from(SignUpWithOAuthReqDto signUpWithOAuthReqDto) {
        return SignUpReqDto.builder()
                .loginId(signUpWithOAuthReqDto.getLoginId())
                .password(signUpWithOAuthReqDto.getPassword())
                .email(signUpWithOAuthReqDto.getEmail())
                .name(signUpWithOAuthReqDto.getName())
                .birthDate(signUpWithOAuthReqDto.getBirthDate())
                .gender(signUpWithOAuthReqDto.getGender())
                .nickname(signUpWithOAuthReqDto.getNickname())
                .userRole(signUpWithOAuthReqDto.getUserRole())
                .build();
    }

    public Member toEntity(String inputPassword) {
        return Member.builder()
                .memberUuid(UUID.randomUUID().toString())
                .loginId(loginId)
                .password(inputPassword)
                .email(email)
                .name(name)
                .birthDate(birthDate)
                .gender(gender)
                .nickname(nickname)
                .userRole(userRole)
                .deletedStatus(false)
                .build();
    }
}