package com.unionclass.memberservice.domain.agreement.dto.in;

import com.unionclass.memberservice.domain.agreement.vo.in.UpdateAgreementReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateAgreementReqDto {

    private Long agreementUuid;
    private String agreementName;
    private String agreementContent;
    private Boolean required;

    @Builder
    public UpdateAgreementReqDto(Long agreementUuid, String agreementName, String agreementContent, Boolean required) {
        this.agreementUuid = agreementUuid;
        this.agreementName = agreementName;
        this.agreementContent = agreementContent;
        this.required = required;
    }

    public static UpdateAgreementReqDto of(String agreementUuid, UpdateAgreementReqVo updateAgreementReqVo) {
        return UpdateAgreementReqDto.builder()
                .agreementUuid(Long.parseLong(agreementUuid))
                .agreementName(updateAgreementReqVo.getAgreementName())
                .agreementContent(updateAgreementReqVo.getAgreementContent())
                .required(updateAgreementReqVo.getRequired())
                .build();
    }
}
