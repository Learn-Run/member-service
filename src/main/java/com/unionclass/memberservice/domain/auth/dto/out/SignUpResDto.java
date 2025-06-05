package com.unionclass.memberservice.domain.auth.dto.out;

import com.unionclass.memberservice.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpResDto {

    private String memberUuid;

    @Builder
    public SignUpResDto(String memberUuid) {
        this.memberUuid = memberUuid;
    }

    public static SignUpResDto from(Member member) {
        return SignUpResDto.builder()
                .memberUuid(member.getMemberUuid())
                .build();
    }
}
