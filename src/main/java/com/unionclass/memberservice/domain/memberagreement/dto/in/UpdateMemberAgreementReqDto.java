package com.unionclass.memberservice.domain.memberagreement.dto.in;

import com.unionclass.memberservice.domain.memberagreement.vo.in.UpdateMemberAgreementReqVo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateMemberAgreementReqDto {

    private String memberUuid;
    private Long agreementUuid;
    private Boolean agreementStatus;

    @Builder
    public UpdateMemberAgreementReqDto(String memberUuid, Long agreementUuid, Boolean agreementStatus) {
        this.memberUuid = memberUuid;
        this.agreementUuid = agreementUuid;
        this.agreementStatus = agreementStatus;
    }

    public static UpdateMemberAgreementReqDto of(
            String memberUuid,
            UpdateMemberAgreementReqVo updateMemberAgreementReqVo
    ) {
        return UpdateMemberAgreementReqDto.builder()
                .memberUuid(memberUuid)
                .agreementUuid(Long.parseLong(updateMemberAgreementReqVo.getAgreementUuid()))
                .agreementStatus(updateMemberAgreementReqVo.getAgreementStatus())
                .build();
    }
}
