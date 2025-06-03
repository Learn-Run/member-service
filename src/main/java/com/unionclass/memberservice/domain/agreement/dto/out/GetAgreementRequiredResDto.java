package com.unionclass.memberservice.domain.agreement.dto.out;

import com.unionclass.memberservice.domain.agreement.entity.Agreement;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetAgreementRequiredResDto {

    private Boolean required;

    @Builder
    public GetAgreementRequiredResDto(Boolean required) {
        this.required = required;
    }

    public static GetAgreementRequiredResDto from(Agreement agreement) {
        return GetAgreementRequiredResDto.builder()
                .required(agreement.getRequired())
                .build();
    }
}
