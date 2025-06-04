package com.unionclass.memberservice.domain.memberagreement.dto.in;

import com.unionclass.memberservice.domain.memberagreement.entity.MemberAgreement;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterMemberAgreementReqDto {

    private String memberUuid;
    private Long agreementUuid;
    private Boolean agreementStatus;

    @Builder
    public RegisterMemberAgreementReqDto(String memberUuid, Long agreementUuid, Boolean agreementStatus) {
        this.memberUuid = memberUuid;
        this.agreementUuid = agreementUuid;
        this.agreementStatus = agreementStatus;
    }

    public MemberAgreement toEntity() {
        return MemberAgreement.builder()
                .memberUuid(memberUuid)
                .agreementUuid(agreementUuid)
                .status(agreementStatus)
                .build();
    }
}
