package com.unionclass.memberservice.domain.oauth.vo.in;

import com.unionclass.memberservice.domain.member.enums.Gender;
import com.unionclass.memberservice.domain.memberagreement.vo.in.RegisterMemberAgreementReqVo;
import com.unionclass.memberservice.domain.oauth.enums.Provider;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class SignUpWithOAuthReqVo {

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

    private List<RegisterMemberAgreementReqVo> agreementCheckList;

    @NotNull(message = "소셜 로그인 제공자를 입력해주세요. (KAKAO)")
    private Provider provider;

    @NotBlank(message = "소셜 계정 식별자를 입력해주세요.")
    private String providerAccountId;
}
