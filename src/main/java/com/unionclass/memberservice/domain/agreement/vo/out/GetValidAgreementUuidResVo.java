package com.unionclass.memberservice.domain.agreement.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetValidAgreementUuidResVo {

    private Long agreementUuid;

    @Builder
    public GetValidAgreementUuidResVo(Long agreementUuid) {
        this.agreementUuid = agreementUuid;
    }
}
