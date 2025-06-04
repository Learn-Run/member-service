package com.unionclass.memberservice.domain.memberagreement.dto.in;

import com.unionclass.memberservice.domain.memberagreement.entity.MemberAgreement;
import com.unionclass.memberservice.domain.memberagreement.vo.in.RegisterMemberAgreementReqVo;
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

    public static RegisterMemberAgreementReqDto from(RegisterMemberAgreementReqVo registerMemberAgreementReqVo) {
        return RegisterMemberAgreementReqDto.builder()
                .memberUuid(registerMemberAgreementReqVo.getMemberUuid())
                .agreementUuid(registerMemberAgreementReqVo.getAgreementUuid())
                .agreementStatus(registerMemberAgreementReqVo.getAgreementStatus())
                .build();
    }

    public MemberAgreement toEntity() {
        return MemberAgreement.builder()
                .memberUuid(memberUuid)
                .agreementUuid(agreementUuid)
                .status(agreementStatus)
                .build();
    }
}
