package com.unionclass.memberservice.member.vo.out;

import com.unionclass.memberservice.member.enums.Gender;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class GetMyInfoResVo {

    private String email;
    private Gender gender;
    private LocalDate birthDate;

    @Builder
    public GetMyInfoResVo(String email, Gender gender, LocalDate birthDate) {
        this.email = email;
        this.gender = gender;
        this.birthDate = birthDate;
    }
}
