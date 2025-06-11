package com.unionclass.memberservice.domain.member.dto.out;

import com.unionclass.memberservice.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateMemberResDto {

    private String memberUuid;

    @Builder
    public CreateMemberResDto(String memberUuid) {
        this.memberUuid = memberUuid;
    }

    public static CreateMemberResDto from(Member member) {
        return CreateMemberResDto.builder()
                .memberUuid(member.getMemberUuid())
                .build();
    }
}
