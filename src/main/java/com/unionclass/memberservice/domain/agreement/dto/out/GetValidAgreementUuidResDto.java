package com.unionclass.memberservice.domain.agreement.dto.out;

import com.unionclass.memberservice.domain.agreement.entity.Agreement;
import com.unionclass.memberservice.domain.agreement.vo.out.GetValidAgreementUuidResVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GetValidAgreementUuidResDto {

    private Long agreementUuid;

    @Builder
    public GetValidAgreementUuidResDto(Long agreementUuid) {
        this.agreementUuid = agreementUuid;
    }

    public static GetValidAgreementUuidResDto from(Agreement agreement) {
        return GetValidAgreementUuidResDto.builder()
                .agreementUuid(agreement.getUuid())
                .build();
    }

    public GetValidAgreementUuidResVo toVo() {
        return GetValidAgreementUuidResVo.builder()
                .agreementUuid(agreementUuid)
                .build();
    }
}
