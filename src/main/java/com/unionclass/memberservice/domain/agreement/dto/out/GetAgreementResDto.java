package com.unionclass.memberservice.domain.agreement.dto.out;

import com.unionclass.memberservice.domain.agreement.entity.Agreement;
import com.unionclass.memberservice.domain.agreement.vo.out.GetAgreementResVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetAgreementResDto {

    private Long agreementUuid;
    private String agreementName;
    private String agreementContent;
    private Boolean required;

    @Builder
    public GetAgreementResDto(Long agreementUuid, String agreementName, String agreementContent, Boolean required) {
        this.agreementUuid = agreementUuid;
        this.agreementName = agreementName;
        this.agreementContent = agreementContent;
        this.required = required;
    }

    public static GetAgreementResDto from(Agreement agreement) {
        return GetAgreementResDto.builder()
                .agreementUuid(agreement.getUuid())
                .agreementName(agreement.getName())
                .agreementContent(agreement.getContent())
                .required(agreement.getRequired())
                .build();
    }

    public GetAgreementResVo toVo() {
        return GetAgreementResVo.builder()
                .agreementUuid(agreementUuid.toString())
                .agreementName(agreementName)
                .agreementContent(agreementContent)
                .required(required)
                .build();
    }
}
