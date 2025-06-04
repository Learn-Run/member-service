package com.unionclass.memberservice.domain.auth.dto.out;

import com.unionclass.memberservice.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetMemberUuidResDto {

    private String memberUuid;

    @Builder
    public GetMemberUuidResDto(String memberUuid) {
        this.memberUuid = memberUuid;
    }

    public static GetMemberUuidResDto from(Member member) {
        return GetMemberUuidResDto.builder()
                .memberUuid(member.getMemberUuid())
                .build();
    }
}
