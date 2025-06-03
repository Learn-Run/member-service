package com.unionclass.memberservice.domain.agreement.vo.in;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateAgreementReqVo {

    @NotBlank(message = "약관 이름은 필수 입력값입니다.")
    private String agreementName;

    @NotBlank(message = "약관 내용은 필수 입력값입니다.")
    private String agreementContent;
    private Boolean required;
}
