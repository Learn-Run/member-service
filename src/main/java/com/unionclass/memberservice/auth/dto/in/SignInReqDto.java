package com.unionclass.memberservice.auth.dto.in;

import com.unionclass.memberservice.auth.vo.in.SignInReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInReqDto {

    private String account;
    private String password;

    @Builder
    public SignInReqDto(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public static SignInReqDto from(SignInReqVo signInReqVo) {
        return SignInReqDto.builder()
                .account(signInReqVo.getAccount())
                .password(signInReqVo.getPassword())
                .build();
    }
}
