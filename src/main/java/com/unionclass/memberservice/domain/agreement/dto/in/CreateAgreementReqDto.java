package com.unionclass.memberservice.domain.agreement.dto.in;

import com.unionclass.memberservice.domain.agreement.entity.Agreement;
import com.unionclass.memberservice.domain.agreement.vo.in.CreateAgreementReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateAgreementReqDto {

    private String agreementName;
    private String agreementContent;
    private Boolean required;

    @Builder
    public CreateAgreementReqDto(String agreementName, String agreementContent, Boolean required) {
        this.agreementName = agreementName;
        this.agreementContent = agreementContent;
        this.required = required;
    }

    public static CreateAgreementReqDto from(CreateAgreementReqVo createAgreementReqVo) {
        return CreateAgreementReqDto.builder()
                .agreementName(createAgreementReqVo.getAgreementName())
                .agreementContent(createAgreementReqVo.getAgreementContent())
                .required(createAgreementReqVo.getRequired())
                .build();
    }


    public Agreement toEntity(Long agreementUuid) {
        return Agreement.builder()
                .uuid(agreementUuid)
                .name(agreementName)
                .content(agreementContent)
                .required(required)
                .deleted(false)
                .build();
    }
}
