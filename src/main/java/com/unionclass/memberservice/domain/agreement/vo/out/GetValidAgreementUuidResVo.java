package com.unionclass.memberservice.domain.agreement.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetValidAgreementUuidResVo {

    private String agreementUuid;

    @Builder
    public GetValidAgreementUuidResVo(String agreementUuid) {
        this.agreementUuid = agreementUuid;
    }
}
