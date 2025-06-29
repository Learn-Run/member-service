package com.unionclass.memberservice.domain.oauth.dto.in;

import com.unionclass.memberservice.domain.member.enums.Gender;
import com.unionclass.memberservice.domain.memberagreement.vo.in.RegisterMemberAgreementReqVo;
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

    private String email;
    private String name;
    private LocalDate birthDate;
    private Gender gender;
    private String nickname;
    private List<RegisterMemberAgreementReqVo> registerMemberAgreementReqVoList;
    private Provider provider;
    private String providerAccountId;

    @Builder
    public SignUpWithOAuthReqDto(
            String email, String name, LocalDate birthDate, Gender gender, String nickname,
            List<RegisterMemberAgreementReqVo> registerMemberAgreementReqVoList, Provider provider, String providerAccountId
    ) {
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.nickname = nickname;
        this.registerMemberAgreementReqVoList = registerMemberAgreementReqVoList;
        this.provider = provider;
        this.providerAccountId = providerAccountId;
    }

    public static SignUpWithOAuthReqDto from(SignUpWithOAuthReqVo signUpWithOAuthReqVo) {
        return SignUpWithOAuthReqDto.builder()
                .email(signUpWithOAuthReqVo.getEmail())
                .name(signUpWithOAuthReqVo.getName())
                .birthDate(signUpWithOAuthReqVo.getBirthDate())
                .gender(signUpWithOAuthReqVo.getGender())
                .nickname(signUpWithOAuthReqVo.getNickname())
                .registerMemberAgreementReqVoList(signUpWithOAuthReqVo.getAgreementCheckList())
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
