package com.unionclass.memberservice.oauth.dto.in;

import com.unionclass.memberservice.member.enums.Gender;
import com.unionclass.memberservice.member.enums.UserRole;
import com.unionclass.memberservice.oauth.entity.MemberOAuth;
import com.unionclass.memberservice.oauth.enums.Provider;
import com.unionclass.memberservice.oauth.vo.in.SignUpWithOAuthReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
    private UserRole userRole;
    private Provider provider;
    private String providerAccountId;

    @Builder
    public SignUpWithOAuthReqDto(
            String loginId, String password, String email, String name, LocalDate birthDate,
            Gender gender, String nickname, UserRole userRole, Provider provider, String providerAccountId
    ) {
        this.loginId = loginId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
        this.nickname = nickname;
        this.userRole = userRole;
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
                .userRole(signUpWithOAuthReqVo.getUserRole())
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
