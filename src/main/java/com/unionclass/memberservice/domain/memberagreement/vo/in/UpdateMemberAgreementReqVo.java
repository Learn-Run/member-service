package com.unionclass.memberservice.domain.memberagreement.vo.in;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class UpdateMemberAgreementReqVo {

    @NotNull(message = "agreementUuid 는 필수 입력값입니다.")
    private Long agreementUuid;

    @NotNull(message = "약관 동의 여부은 필수 입력값입니다.")
    private Boolean agreementStatus;
}
