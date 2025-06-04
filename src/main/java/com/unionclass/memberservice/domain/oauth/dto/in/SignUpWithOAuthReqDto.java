package com.unionclass.memberservice.domain.oauth.dto.in;

import com.unionclass.memberservice.domain.member.enums.Gender;
import com.unionclass.memberservice.domain.memberagreement.vo.in.CheckMemberAgreementReqVo;
import com.unionclass.memberservice.domain.oauth.entity.MemberOAuth;
import com.unionclass.memberservice.domain.oauth.enums.Provider;
import com.unionclass.memberservice.domain.oauth.vo.in.SignUpWithOAuthReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class SignUpWithOAuthReqDto {

    private String loginId;
    private String password;
    private String email;
    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private String nickname;
    private List<CheckMemberAgreementReqVo> checkMemberAgreementReqVoList;
    private Provider provider;
    private String providerAccountId;

    @Builder
    public SignUpWithOAuthReqDto(
            String loginId, String password, String email, String name, LocalDate birthDate,
            Gender gender, String nickname, List<CheckMemberAgreementReqVo> checkMemberAgreementReqVoList,
            Provider provider, String providerAccountId
    ) {
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.nickname = nickname;
        this.checkMemberAgreementReqVoList = checkMemberAgreementReqVoList;
        this.provider = provider;
        this.providerAccountId = providerAccountId;
    }

    public static SignUpWithOAuthReqDto from(SignUpWithOAuthReqVo signUpWithOAuthReqVo) {
        return SignUpWithOAuthReqDto.builder()
                .loginId(signUpWithOAuthReqVo.getLoginId())
                .password(signUpWithOAuthReqVo.getPassword())
                .email(signUpWithOAuthReqVo.getEmail())
                .name(signUpWithOAuthReqVo.getName())
                .birthDate(signUpWithOAuthReqVo.getBirthDate())
                .gender(signUpWithOAuthReqVo.getGender())
                .nickname(signUpWithOAuthReqVo.getNickname())
                .checkMemberAgreementReqVoList(signUpWithOAuthReqVo.getCheckMemberAgreementReqVoList())
                .provider(signUpWithOAuthReqVo.getProvider())
                .providerAccountId(signUpWithOAuthReqVo.getProviderAccountId())
                .build();
    }

    public MemberOAuth toEntity(String memberUuid) {
        return MemberOAuth.builder()
                .memberUuid(memberUuid)
                .provider(provider)
                .providerAccountId(providerAccountId)
                .build();
    }
}
