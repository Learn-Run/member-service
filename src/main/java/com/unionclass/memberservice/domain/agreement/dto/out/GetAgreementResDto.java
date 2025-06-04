package com.unionclass.memberservice.domain.agreement.dto.out;

import com.unionclass.memberservice.domain.agreement.entity.Agreement;
import com.unionclass.memberservice.domain.agreement.vo.out.GetAgreementResVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetAgreementResDto {

    private Long agreementId;
    private String agreementName;
    private String agreementContent;
    private Boolean required;

    @Builder
    public GetAgreementResDto(Long agreementId, String agreementName, String agreementContent, Boolean required) {
        this.agreementId = agreementId;
        this.agreementName = agreementName;
        this.agreementContent = agreementContent;
        this.required = required;
    }

    public static GetAgreementResDto from(Agreement agreement) {
        return GetAgreementResDto.builder()
                .agreementId(agreement.getId())
                .agreementName(agreement.getName())
                .agreementContent(agreement.getContent())
                .required(agreement.getRequired())
                .build();
    }

    public GetAgreementResVo toVo() {
        return GetAgreementResVo.builder()
                .agreementId(agreementId)
                .agreementName(agreementName)
                .agreementContent(agreementContent)
                .required(required)
                .build();
    }
}
