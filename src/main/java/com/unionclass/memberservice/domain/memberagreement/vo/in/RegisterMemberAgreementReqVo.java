package com.unionclass.memberservice.domain.memberagreement.vo.in;

import com.unionclass.memberservice.domain.memberagreement.dto.in.RegisterMemberAgreementReqDto;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RegisterMemberAgreementReqVo {

    @NotNull(message = "agreementUuid 는 필수 입력값입니다.")
    private String agreementUuid;

    @NotNull(message = "약관 동의 여부은 필수 입력값입니다.")
    private Boolean agreementStatus;

    @Builder
    public RegisterMemberAgreementReqVo(String agreementUuid, Boolean agreementStatus) {
        this.agreementUuid = agreementUuid;
        this.agreementStatus = agreementStatus;
    }

    public RegisterMemberAgreementReqDto toDto(String memberUuid) {
        return RegisterMemberAgreementReqDto.builder()
                .memberUuid(memberUuid)
                .agreementUuid(Long.parseLong(agreementUuid))
                .agreementStatus(agreementStatus)
                .build();
    }
}
