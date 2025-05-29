package com.unionclass.memberservice.auth.dto.in;

import com.unionclass.memberservice.auth.vo.in.AccountReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountReqDto {

    private String loginId;

    @Builder
    public AccountReqDto(String loginId) { this.loginId = loginId; }

    public static AccountReqDto from(AccountReqVo loginIdReqVo) {
        return AccountReqDto.builder()
                .loginId(loginIdReqVo.getLoginId())
                .build();
    }
}