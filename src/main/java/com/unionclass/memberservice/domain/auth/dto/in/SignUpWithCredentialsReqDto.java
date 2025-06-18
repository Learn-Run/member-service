package com.unionclass.memberservice.domain.auth.dto.in;

import com.unionclass.memberservice.domain.auth.entity.Auth;
import com.unionclass.memberservice.domain.auth.vo.in.SignUpReqVo;
import com.unionclass.memberservice.domain.member.entity.Member;
import com.unionclass.memberservice.domain.member.enums.Gender;
import com.unionclass.memberservice.domain.member.enums.MemberRole;
import com.unionclass.memberservice.domain.memberagreement.vo.in.RegisterMemberAgreementReqVo;
import com.unionclass.memberservice.domain.oauth.dto.in.SignUpWithOAuthReqDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class SignUpWithCredentialsReqDto {

    private String loginId;
    private String password;
    private String email;
    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private String nickname;
    private List<RegisterMemberAgreementReqVo> registerMemberAgreementReqVoList;

    @Builder
    public SignUpWithCredentialsReqDto(String loginId, String password, String email, String name,
                                       LocalDate birthDate, Gender gender, String nickname,
                                       List<RegisterMemberAgreementReqVo> registerMemberAgreementReqVoList
    ) {
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.nickname = nickname;
        this.registerMemberAgreementReqVoList = registerMemberAgreementReqVoList;
    }

    public static SignUpWithCredentialsReqDto from(SignUpReqVo signUpReqVo) {
        return SignUpWithCredentialsReqDto.builder()
                .loginId(signUpReqVo.getLoginId())
                .password(signUpReqVo.getPassword())
                .email(signUpReqVo.getEmail())
                .name(signUpReqVo.getName())
                .birthDate(signUpReqVo.getBirthDate())
                .gender(signUpReqVo.getGender())
                .nickname(signUpReqVo.getNickname())
                .registerMemberAgreementReqVoList(signUpReqVo.getAgreementCheckList())
                .build();
    }

    public Auth toEntity(String memberUuid, String inputPassword) {
        return Auth.builder()
                .memberUuid(memberUuid)
                .loginId(loginId)
                .password(inputPassword)
                .build();
    }
}