package com.unionclass.memberservice.domain.auth.vo.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class GetLoginIdReqVo {

    @NotBlank(message = "아이디를 입력해주세요.")
    private String loginId;
}
