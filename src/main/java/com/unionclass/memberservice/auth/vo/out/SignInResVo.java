package com.unionclass.memberservice.auth.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInResVo {

    private String memberUuid;
    private String accessToken;

    @Builder
    public SignInResVo(String memberUuid, String accessToken) {
        this.memberUuid = memberUuid;
        this.accessToken = accessToken;
    }
}
