package com.unionclass.memberservice.auth.dto.in;

import com.unionclass.memberservice.auth.vo.in.AccountReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AccountReqDto {

    private String account;

    @Builder
    public AccountReqDto(String account) { this.account = account; }

    public static AccountReqDto from(AccountReqVo loginIdReqVo) {
        return AccountReqDto.builder()
                .account(loginIdReqVo.getAccount())
                .build();
    }
}