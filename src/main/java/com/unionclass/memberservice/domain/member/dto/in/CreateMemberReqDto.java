package com.unionclass.memberservice.domain.member.dto.in;

import com.unionclass.memberservice.domain.auth.dto.in.SignUpWithCredentialsReqDto;
import com.unionclass.memberservice.domain.member.entity.Member;
import com.unionclass.memberservice.domain.member.enums.Gender;
import com.unionclass.memberservice.domain.member.enums.MemberRole;
import com.unionclass.memberservice.domain.oauth.dto.in.SignUpWithOAuthReqDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@NoArgsConstructor
public class CreateMemberReqDto {

    private String email;
    private String name;
    private LocalDate birthDate;
    private Gender gender;

    @Builder
    public CreateMemberReqDto(String email, String name, LocalDate birthDate, Gender gender) {
        this.email = email;
        this.name = name;
        this.birthDate = birthDate;
        this.gender = gender;
    }

    public static CreateMemberReqDto from(SignUpWithCredentialsReqDto signUpWithCredentialsReqDto) {
        return CreateMemberReqDto.builder()
                .email(signUpWithCredentialsReqDto.getEmail())
                .name(signUpWithCredentialsReqDto.getName())
                .birthDate(signUpWithCredentialsReqDto.getBirthDate())
                .gender(signUpWithCredentialsReqDto.getGender())
                .build();
    }

    public static CreateMemberReqDto from(SignUpWithOAuthReqDto signUpWithOAuthReqDto) {
        return CreateMemberReqDto.builder()
                .email(signUpWithOAuthReqDto.getEmail())
                .name(signUpWithOAuthReqDto.getName())
                .birthDate(signUpWithOAuthReqDto.getBirthDate())
                .gender(signUpWithOAuthReqDto.getGender())
                .build();
    }

    public Member toEntity(MemberRole memberRole) {
        return Member.builder()
                .memberUuid(UUID.randomUUID().toString())
                .email(email)
                .name(name)
                .birthDate(birthDate)
                .gender(gender)
                .memberRole(memberRole)
                .deleted(false)
                .build();
    }
}
