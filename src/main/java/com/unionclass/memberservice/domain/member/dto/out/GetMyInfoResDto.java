package com.unionclass.memberservice.domain.member.dto.out;

import com.unionclass.memberservice.domain.member.entity.Member;
import com.unionclass.memberservice.domain.member.enums.Gender;
import com.unionclass.memberservice.domain.member.vo.out.GetMyInfoResVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class GetMyInfoResDto {

    private String email;
    private Gender gender;
    private LocalDate birthDate;

    @Builder
    public GetMyInfoResDto(String email, Gender gender, LocalDate birthDate) {
        this.email = email;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public static GetMyInfoResDto from(Member member) {
        return GetMyInfoResDto.builder()
                .email(member.getEmail())
                .gender(member.getGender())
                .birthDate(member.getBirthDate())
                .build();
    }

    public GetMyInfoResVo toVo() {
        return GetMyInfoResVo.builder()
                .email(email)
                .gender(gender)
                .birthDate(birthDate)
                .build();
    }
}
