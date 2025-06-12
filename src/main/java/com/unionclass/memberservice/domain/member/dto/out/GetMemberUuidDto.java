package com.unionclass.memberservice.domain.member.dto.out;

import com.unionclass.memberservice.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetMemberUuidDto {

    private String memberUuid;

    @Builder
    public GetMemberUuidDto(String memberUuid) {
        this.memberUuid = memberUuid;
    }

    public static GetMemberUuidDto from(Member member) {
        return GetMemberUuidDto.builder()
                .memberUuid(member.getMemberUuid())
                .build();
    }
}
