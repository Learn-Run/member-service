package com.unionclass.memberservice.domain.agreement.vo.out;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetAgreementResVo {

    private String agreementName;
    private String agreementContent;
    private Boolean required;

    @Builder
    public GetAgreementResVo(String agreementName, String agreementContent, Boolean required) {
        this.agreementName = agreementName;
        this.agreementContent = agreementContent;
        this.required = required;
    }
}
