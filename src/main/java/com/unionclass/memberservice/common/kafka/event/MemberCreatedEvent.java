package com.unionclass.memberservice.common.kafka.event;

import com.unionclass.memberservice.domain.auth.dto.in.SignUpWithCredentialsReqDto;
import com.unionclass.memberservice.domain.oauth.dto.in.SignUpWithOAuthReqDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberCreatedEvent {

    private String memberUuid;
    private String nickname;

    @Builder
    public MemberCreatedEvent(String memberUuid, String nickname) {
        this.memberUuid = memberUuid;
        this.nickname = nickname;
    }

    public static MemberCreatedEvent of(
            String memberUuid, SignUpWithCredentialsReqDto dto
    ) {
        return MemberCreatedEvent.builder()
                .memberUuid(memberUuid)
                .nickname(dto.getNickname())
                .build();
    }

    public static MemberCreatedEvent of(
            String memberUuid, SignUpWithOAuthReqDto dto
    ) {
        return MemberCreatedEvent.builder()
                .memberUuid(memberUuid)
                .nickname(dto.getNickname())
                .build();
    }
}
