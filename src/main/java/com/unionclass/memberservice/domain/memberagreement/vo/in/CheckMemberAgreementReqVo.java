package com.unionclass.memberservice.domain.memberagreement.vo.in;

import com.unionclass.memberservice.domain.memberagreement.dto.in.RegisterMemberAgreementReqDto;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CheckMemberAgreementReqVo {

    @NotNull(message = "agreementUuid 는 필수 입력값입니다.")
    private Long agreementUuid;

    @NotNull(message = "약관 동의 여부은 필수 입력값입니다.")
    private Boolean agreementStatus;

    @Builder
    public CheckMemberAgreementReqVo(Long agreementUuid, Boolean agreementStatus) {
        this.agreementUuid = agreementUuid;
        this.agreementStatus = agreementStatus;
    }

    public RegisterMemberAgreementReqDto toDto(String memberUuid) {
        return RegisterMemberAgreementReqDto.builder()
                .memberUuid(memberUuid)
                .agreementUuid(agreementUuid)
                .agreementStatus(agreementStatus)
                .build();
    }
}
